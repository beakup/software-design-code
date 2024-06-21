
cfg = {
    "CamoFormer":{
        'BUSI': {
            "img_size": 224,
            'mean': (0.485, 0.456, 0.406),
            'std': (0.229, 0.224, 0.225)
        },
        'Cell': {
            "img_size": 448,
            'mean': (0.485, 0.456, 0.406),
            'std': (0.229, 0.224, 0.225)
        }
    },
    "TransXUnet":{
        'BUSI': {
            'img_size': 224,
            'mean': (0.485, 0.456, 0.406),
            'std': (0.229, 0.224, 0.225)
        },
        'Cell': {
            'img_size': 448,
            'mean': (0.485, 0.456, 0.406),
            'std': (0.229, 0.224, 0.225)
        }
    },
    "RepVGG": {
        'BUSI': {
            'img_size': 224,
            'mean': (0.330, 0.330, 0.329),
            'std': (0.393, 0.393, 0.393)
        },
        'Cell': {
            'img_size': 224,
            'mean': (0.330, 0.330, 0.329),
            'std': (0.393, 0.393, 0.393)
        }
    },
    "DexFormer":{
        'BUSI': {
            'img_size': 224,
            'mean': (0.330, 0.330, 0.329),
            'std': (0.393, 0.393, 0.393)
        },
        'Cell': {
            'img_size': 224,
            'mean': (0.330, 0.330, 0.329),
            'std': (0.393, 0.393, 0.393)
        }
    }
}

def get_cfg(model_name,dataset):
    return cfg[model_name][dataset]["img_size"],cfg[model_name][dataset]["mean"],cfg[model_name][dataset]["std"],