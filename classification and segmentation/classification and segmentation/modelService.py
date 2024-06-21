import cv2
import numpy as np
import torch
from torch import package
from PIL import Image
from imageio import imsave
from torch.backends import cudnn
from Config.config import get_cfg
import time

use_cuda = True
cudnn.fastest = True
cudnn.benchmark = True

current_model = None
data_set = None
model = None
img_size = 224
mean = (0.485, 0.456, 0.406)
std = (0.229, 0.224, 0.225)
root_path = "../../project/Project code/files"

resource_name = "model.pkl"


def getModel():
    model_path = "weights/{}/{}/Net.pkl".format(str(current_model), str(data_set))
    device = 'cuda' if torch.cuda.is_available() and use_cuda else 'cpu'
    imp = package.PackageImporter(model_path)
    loaded_model = imp.load_pickle(str(current_model), resource_name)
    loaded_model.to(device)
    return loaded_model


def service(image_key: str, image_link: str, info: dict):
    global model, img_size, current_model, data_set, mean, std
    T1 = time.time()
    if current_model != info[ "ModelName" ] or data_set != info[ "DataSet" ]:
        current_model = info[ "ModelName" ]
        data_set = info[ "DataSet" ]
        model = getModel()
        img_size, mean, std = get_cfg(current_model, data_set)

        T2 = time.time()
        print("加载模型耗时:{}ms".format((T2 - T1) * 1000))

    image_link = image_link.split('/')[ -1 ].split("?")[ 0 ]

    image = Image.open(root_path + "/" + image_link)

    if info[ "Task" ] == "seg":
        out = seg(image, image_key, )
    else:
        out = cls(image)
    T3 = time.time()
    print("总耗时:{}ms".format((T3 - T1) * 1000))
    return out


def cls(image):
    frame = np.array(image)

    # convert to cv format
    frames = frame[ :, :, ::-1 ]

    ori_imgs, input_img = image_preprocess(frames, max_size=img_size, mean=mean, std=std)
    if use_cuda:
        x = torch.stack([ torch.from_numpy(img).cuda() for img in input_img ], 0)
    else:
        x = torch.stack([ torch.from_numpy(img) for img in input_img ], 0)

    x = x.to(torch.float32).permute(0, 3, 1, 2)

    with torch.no_grad():
        predict = model(x)
        return predict


def seg(image, image_key):
    # convert image to array
    frame = np.array(image)

    # convert to cv format
    frames = frame[ :, :, ::-1 ]

    ori_imgs, input_img = image_preprocess(frames, max_size=img_size, mean=mean, std=std)

    if use_cuda:
        x = torch.stack([ torch.from_numpy(img).cuda() for img in input_img ], 0)
    else:
        x = torch.stack([ torch.from_numpy(img) for img in input_img ], 0)

    x = x.to(torch.float32).permute(0, 3, 1, 2)

    with torch.no_grad():
        predict = model(x)
        predict=torch.sigmoid(predict)
        if data_set == "Cell":
            out = displayWithMask(ori_imgs, predict, image_key)
        else:
            out = displayWithContours(ori_imgs, predict, image_key)
        return out


def image_preprocess(image_path, max_size, mean, std):
    image_path = cv2.cvtColor(image_path, cv2.COLOR_RGB2BGR)
    ori_imgs = [ image_path ]
    imgs_meta = [ cv2.resize(img, dsize=[ max_size, max_size ], interpolation=cv2.INTER_LINEAR) for img in ori_imgs ]
    normalized_imgs = [ (img / 255 - mean) / std for img in imgs_meta ]
    return ori_imgs[ 0 ], normalized_imgs


def displayWithMask(img, pred, image_key):
    img = np.uint8(img)

    pred_y = np.array(pred.squeeze().to('cpu'))
    pred_y = cv2.resize(pred_y, dsize=[ img.shape[ 1 ], img.shape[ 0 ] ], interpolation=cv2.INTER_LINEAR)
    pred_y = np.uint8(np.where(pred_y > 0.5, 255, 0))

    PRED_OUT = cv2.cvtColor(pred_y, cv2.COLOR_GRAY2BGR)
    PRED_OUT[ :, :, 0 ] = (PRED_OUT[ :, :, 0 ] > 1) * 0
    PRED_OUT[ :, :, 1 ] = (PRED_OUT[ :, :, 1 ] > 1) * 255
    PRED_OUT[ :, :, 2 ] = (PRED_OUT[ :, :, 2 ] > 1) * 0
    alpha = 1
    # beta 为第二张图片的透明度
    beta = 0.9
    gamma = 2

    PRED_OUT = cv2.addWeighted(PRED_OUT, alpha, img, beta, gamma)
    imsave(root_path + "/{}.png".format(image_key), PRED_OUT)

    return str(image_key) + ".png"


def displayWithContours(img, pred, image_key):
    img = np.uint8(img)
    pred_y = np.array(pred.squeeze().to('cpu'))
    pred_y = cv2.resize(pred_y, dsize=[ img.shape[ 1 ], img.shape[ 0 ] ], interpolation=cv2.INTER_LINEAR)
    pred_y = np.uint8(np.where(pred_y > 0.5, 255, 0))
    ret, pred_y = cv2.threshold(pred_y, 1, 255, 0)
    pred_y, ret = cv2.findContours(pred_y, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_TC89_KCOS)
    PRED_OUT = cv2.drawContours(img.copy(), pred_y, -1, (255, 163, 163), 3)  # 红色

    imsave(root_path + "/{}.png".format(image_key), PRED_OUT)

    return str(image_key) + ".png"
