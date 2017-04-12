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
    "1": [
      {
        "id": 1,
        "name": "test1",
        "classifyId": 1,
        "classifyName": "美容产品",
        "shopId": 1,
        "originPrice": 1000,
        "stock": 1000,
        "salesVolume": 1000,
        "minPrice": 100,
        "newPrice": 170,
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
        "minPrice": 100,
        "newPrice": 170,
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
    "outstanding_shop_info": {
      "outstanding_shop_id": "1",
      "outstanding_shop_name": "测试商店1"
    },
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
        "minPrice": 100,
        "newPrice": 170,
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
        "minPrice": 100,
        "newPrice": 170,
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

---
#####3.首页获取用户信息
url: /main/getUserInfo

method: get

request: `{}`

response:

用户登录时:
```json
{
  "code": 200,
  "msg": {
    "point": 100,
    "price": 70,//可当xx元使用
    "coin": 100
  }
}
```
用户未登录:
```json
{
  "code": 200,
  "msg": null
}
```

---

分类部分

---
#####1.获取某分类下商品:

url: classify/getGoods

method: get

request:
```json
{
  "classifyId":1 //0表示优品牌
}
```

response:
```json
{
  "code": 200,
  "msg": [
    {
      "id": 1,
      "name": "test1",
      "classifyId": 1,
      "classifyName": "美容产品",
      "shopId": 1,
      "originPrice": 1000,
      "stock": 1000,
      "salesVolume": 1000,
      "minPrice": 100,
      "newPrice": 170,
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
      "minPrice": 100,
      "newPrice": 170,
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
```
---

店铺部分

---
#####1.获取店铺中所有商品

url: shop/getAll/:shopId

method: get

request
```json
{}
```

response:
```json
{
  "code": 200,
  "msg": [
    {
      "id": 1,
      "name": "test1",
      "classifyId": 1,
      "classifyName": "美容产品",
      "shopId": 1,
      "originPrice": 1000,
      "stock": 1000,
      "salesVolume": 1000,
      "minPrice": 100,
      "newPrice": 170,
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
      "minPrice": 100,
      "newPrice": 170,
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
```
---
#####2.获取热销商品

url: shop/getHotSale/:shopId

method: get

request
```json
{}
```

response: 同上一个接口

---
#####3.获取店铺首页内容

url: shop/getShopMainPage/:shopId

method: get

request
```json
{}
```

response:
```json
{
  "code": 200,
  "msg": {
    "headImgUrl": "头图地址1",
    "goods": [
      {
        "id": 1,
        "name": "test1",
        "classifyId": 1,
        "classifyName": "美容产品",
        "shopId": 1,
        "originPrice": 1000,
        "stock": 1000,
        "salesVolume": 1000,
        "minPrice": 100,
        "newPrice": 170,
        "maxPoint": 100,
        "minPoint": 100,
        "maxCoin": 100,
        "minCoin": 100,
        "headImg": "aaaaaaa",
        "contentImg": "aaaaaa",
        "content": "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
        "status": 1
      }
    ],
    "mainImgUrl": "主页图片地址1"
  }
}
```
---
#####4.获取店铺活动图片

url: shop/getActivityImgUrl/:shopId

method: get

request
```json
{}
```

response:
```json
{
  "code": 200,
  "msg": "图片地址1"
}
```
---
#####4.获取店铺客服电话

url: shop/getTelephone/:shopId

method: get

request
```json
{}
```

response:
```json
{
  "code": 200,
  "msg": "客服电话"
}
```