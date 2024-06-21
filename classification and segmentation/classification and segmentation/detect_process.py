import json
import time
import traceback

import redis as redis

import modelService as service

# redis cache client
RedisCache = redis.StrictRedis(host="localhost", port=6379, db=0)

# the queue of expect to detect
IMAGE_QUEUE = "imageQueue"

# slice size every foreach

BATCH_SIZE = 32
# server sleep when queue>0

SERVER_SLEEP = 0.1
# server sleep when queue=0
SERVER_SLEEP_IDLE = 0.5


def detect_process():
    while True:
        # 从redis中获取预测图像队列
        queue = RedisCache.lrange(IMAGE_QUEUE, 0, BATCH_SIZE - 1)
        if len(queue) < 1:
            time.sleep(SERVER_SLEEP)
            continue

        print("classify_process is running")

        # 遍历队列
        for item in queue:
            try:
                # step 1. 获取队列中的图像信息
                item = json.loads(item)
                image_key = item.get("imageKey")
                image_link = item.get("imageUrl")
                Info = dict()
                Info[ "ModelName" ] = item.get("ModelName")
                Info[ "DataSet" ] = item.get("DataSet")
                Info[ "Task" ] = item.get("Task")

                print(item)

                # step 2. detect image 识别图片
                out_link = service.service(image_key, image_link, Info)
                print(out_link)
                RedisCache.hset(name=image_key, key="consultOut", value=out_link)
            except:
                traceback.print_exc()

            RedisCache.ltrim(IMAGE_QUEUE, 1, -1)

        time.sleep(SERVER_SLEEP)


if __name__ == '__main__':
    print("start process")
    detect_process()
