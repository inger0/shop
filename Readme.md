唐僧商城前后端接口文档
===================
***
备注:返回内容有如下统一的数据格式:
``` javascript
{
    code:200
    msg:{}
}
```
code有如下几种情况
```
200:成功
500或其他:错误
```
数据一律从msg属性中获取
***
<br/>
<br/>
<br/>
首页部分

-------

##### 1.获取分类
url: main/getClassify

method: get

request: `{}`

response:
```json
{
  "code": 200,
  "msg": [
    {
      "id": 1,
      "classifyName": "保健品"
    },
    {
      "id": 2,
      "classifyName": "美容产品"
    }
  ]
}
```

---
#####2.获取首页商品列表

url: main/getMainPageGoods

method: get

request: `{}`

response:
```json
{
  "code": 200,
  "msg": {
    "美容产品": [
      {
        "id": 1,
        "name": "test1",
        "classifyId": 1,
        "classifyName": "美容产品",
        "shopId": 1,
        "originPrice": 1000,
        "stock": 1000,
        "salesVolume": 1000,
        "maxPoint": 100,
        "minPoint": 100,
        "maxCoin": 100,
        "minCoin": 100,
        "headImg": "aaaaaaa",
        "contentImg": "aaaaaa",
        "content": "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
        "status": 0
      },
      {
        "id": 2,
        "name": "test2",
        "classifyId": 1,
        "classifyName": "美容产品",
        "shopId": 1,
        "originPrice": 1000,
        "stock": 1000,
        "salesVolume": 1000,
        "maxPoint": 100,
        "minPoint": 100,
        "maxCoin": 100,
        "minCoin": 100,
        "headImg": "aaaaaaa",
        "contentImg": "aaaaaa",
        "content": "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
        "status": 0
      }
    ],
    "outstanding_shop_goods": [
      {
        "id": 1,
        "name": "test1",
        "classifyId": 1,
        "classifyName": "美容产品",
        "shopId": 1,
        "originPrice": 1000,
        "stock": 1000,
        "salesVolume": 1000,
        "maxPoint": 100,
        "minPoint": 100,
        "maxCoin": 100,
        "minCoin": 100,
        "headImg": "aaaaaaa",
        "contentImg": "aaaaaa",
        "content": "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
        "status": 0
      },
      {
        "id": 2,
        "name": "test2",
        "classifyId": 1,
        "classifyName": "美容产品",
        "shopId": 1,
        "originPrice": 1000,
        "stock": 1000,
        "salesVolume": 1000,
        "maxPoint": 100,
        "minPoint": 100,
        "maxCoin": 100,
        "minCoin": 100,
        "headImg": "aaaaaaa",
        "contentImg": "aaaaaa",
        "content": "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
        "status": 0
      }
    ]
  }
}
```

