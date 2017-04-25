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
      "classifyName": "保健品",
      "classifyImg":"/img/cc1.png"
    },
    {
      "id": 2,
      "classifyName": "美容产品",
      "classifyImg":"/img/cc1.png"
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
        "id": 4,
        "name": "测试商品4",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 5,
        "name": "测试商品5",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 6,
        "name": "测试商品6",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 7,
        "name": "测试商品7",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      }
    ],
    "2": [
      {
        "id": 8,
        "name": "测试商品8",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 9,
        "name": "测试商品9",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 10,
        "name": "测试商品10",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 11,
        "name": "测试商品11",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 12,
        "name": "测试商品12",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 13,
        "name": "测试商品13",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      }
    ],
    "outstanding_shop_info": {
      "outstanding_shop_id": "1",
      "outstanding_shop_name": "测试商店1"
    },
    "outstanding_shop_goods": [
      {
        "id": 4,
        "name": "测试商品4",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 5,
        "name": "测试商品5",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 6,
        "name": "测试商品6",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 7,
        "name": "测试商品7",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 8,
        "name": "测试商品8",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 9,
        "name": "测试商品9",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 10,
        "name": "测试商品10",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 11,
        "name": "测试商品11",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 12,
        "name": "测试商品12",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      },
      {
        "id": 13,
        "name": "测试商品13",
        "classifyId": 2,
        "classifyName": "保健坊",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 7
      }
    ],
    "advertisement": [
      {
        "id": 1,
        "name": "测试商品1",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 8
      },
      {
        "id": 2,
        "name": "测试商品2",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 8
      },
      {
        "id": 3,
        "name": "测试商品3",
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
        "headImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "contentImg": "/imgs/TB2MDicoYlmpuFjSZFlXXbdQXXa_!!2348132577.png",
        "content": "品牌名称：优趣关注<br/> 产品参数： 包装体积: 27*20*17<br/> 原产地: 中国品牌: 优趣毛重: 1.6kg生产厂家名称: 上海味它宠物用品有限公司生产厂家地址: 上海市嘉定区南翔镇纬五路228号重量(g): 1500货号: 05006适用阶段: 全阶段<br/> 食品口味: 鱼肉味<br/> 剩余保质期: 6个月以上",
        "status": 8
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
    "headImg":"/img/1492605839775_[B@4b3a50f7.png",
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
#####5.获取店铺客服电话

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
----
订单与购物车部分

----

#####1.获取商品详情
(获取以下信息需要先登录 调用 account/login)

url: good/getGoodContent/:goodId

method: get

request: ``{}``

response:
```json
{
  "code": 200,
  "msg": {
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
    "status": 0,
    "telephone":"1234567890"
  }
}
```

----
#####2.将商品添加至购物车

url: good/addGoodToCart

method: post

request:
```json
{
  "goodId":1
}
```

response:
```json
{
  "code":200,
  "msg": {
    "count":3  }
}
```
---

#####3.获取购物车中订单和商品信息

url: good/getOrdersInCart

request: ``{}``

response:
```json
{
  "code": 200,
  "msg": [
    {
      "orderId": 1,
      "goodId": 1,
      "goodCount": 2,
      "userId": 1,
      "usedCoin": null,
      "usedPoint": null,
      "transportWay": null,
      "addressId": null,
      "amountPrice": null,
      "name": "test1",
      "headImg": "aaaaaaa",
      "classifyId": 1,
      "classifyName": "美容产品",
      "shopId": 1,
      "shopName": "测试商店1",
      "originPrice": 1000,
      "stock": 1000,
      "salesVolume": 1000,
      "minPrice": 100,
      "newPrice": 170,
      "maxPoint": 100,
      "minPoint": 100,
      "maxCoin": 100,
      "minCoin": 100,
      "describeStatus": null
    }
  ]
}
```
----

#####4.删除购物车中商品

url: good/deleteGoodFromCart

method: post

request:
```json
{
  "orderId":1
}
```

response:
```json
{
  "code": 200,
  "msg":[]
}
```
---
#####5.改变购物车中商品数量

url: good/changeOrderCount/:orderId

method: post

request:
```json
{
  "count":5
}
```

response:
```json
{
  "code":200,
  "msg":{
    "count":5
  }
}
```

----
#####6.放弃订单付款

url: good/abandonOrder

method: post

request:
```json
{
  "orderIds":[1,2,3,4,5,6,7]
}
```

response:
```json
{
  "code":200,
  "msg":[]
}
```
----
#####7.订单付款回调

url: good/payedOrder

method: post

request:
```json
{
  "orderIds":[1,2,3,4,5,6,7]
}
```

response:
```json
{
  "code":200,
  "msg":[]
}
```
----
#####8.获取部落币 部落分比率

url: good/getRate

method: get

request: ``{}``

response:
```json
{
  "code": 200,
  "msg": {
    "pointRate": 0.3,
    "coinRate": 0.3
  }
}
```
-----
#####9.获取订单管理信息
url: getOrderInfo/:status

status有三种: All wait4pay wait4receive

method: get

request: ``{}``

response:
```json
{
  "code": 200,
  "msg": [
    {
      "orderId":1,
      "goodId": 1,
      "goodCount": 2,
      "userId": 1,
      "usedCoin": null,
      "usedPoint": null,
      "transportWay": null,
      "addressId": null,
      "amountPrice": null,
      "name": "test1",
      "classifyId": 1,
      "classifyName": "美容产品",
      "shopId": 1,
      "shopName":"店铺名1",
      "originPrice": 1000,
      "stock": 1000,
      "salesVolume": 1000,
      "minPrice": 100,
      "newPrice": 170,
      "maxPoint": 100,
      "minPoint": 100,
      "maxCoin": 100,
      "minCoin": 100,
      "describeStatus": null
    }
  ]
}
```

-----
#####10.商品搜索接口

url: good/search

method: post

request:
```json
{
  "goodName":"测试"
}
```

response:
```json
{
  "code": 200,
  "msg": [
    {
      "id": 1,
      "name": "测试商品1",
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
      "name": "测试商品2",
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

-----

用户管理部分

-----
#####1.获取某用户所有地址

url: account/getAddress

method: get

request:``{}``

response:
```json
{
  "code": 200,
  "msg": [
    {
      "id": 1,
      "userId": 1,
      "reciever": "于景洋",
      "address": "山东省济南市历下区舜华路1500号",
      "recieverTelephone": "17621181235",
      "mailNumber": "lovesyxfuffy@gmail.com",
      "status": 0
    }
  ]
}
```

#####2.添加地址

url: account/addAddress

method: post

request:
```json
{
	"reciever":"于景洋",
	"address":"山东省济南市历下区舜华路1500号",
	"recieverTelephone":"17621181235",
	"mailNumber":"lovesyxfuffy@gmail.com",
	"status":"0"//1表示设为默认地址 0表示没有设为默认地址
}
```

response:
```json
{
  "code":200,
  "msg":[]
}
```
-----

#####3.获取某个地址的详细信息

url:  account/getOneAddress/:addressId

method: get

request: ``{}``

response:
```json
{
  "code": 200,
  "msg": {
    "id": 1,
    "userId": 1,
    "reciever": "于景洋",
    "address": "山东省济南市历下区舜华路1500号",
    "recieverTelephone": "17621181235",
    "mailNumber": "lovesyxfuffy@gmail.com",
    "status": 0
  }
}
```
#####4.上传用户头像

url: account/uploadHeadImg

method: post

Content-Type: multipart/form-data

request 代码示例:
```JavaScript
import React from 'react'
import {connect} from 'react-redux'
import FileUpload from 'react-fileupload'

class MyFileUpload extends React.Component {

    componentWillMount() {

    }

    render() {
        const options = {
            baseUrl: 'http://localhost:8080/account/uploadHeadImg',
            fileFieldName: "file"
        }
        console.log(options)
        return (

            <FileUpload options={options}>
                <button ref="chooseBtn">choose</button>
                <button ref="uploadBtn">upload</button>
            </FileUpload>
        )

    }
}
```

response:
```json
{
    "code":200,
    "msg":""
}
```

-----

#####5.更改支付密码

url: account/changePayPassword

method: post

request:
```json
{
  "originalPassword":"123456",
  "newPassword":"12345678"
}
```

response:
```json
{
  "code":200,
  "msg":[]
}
```

------

#####6.获取原手机号

method: get

request:``{}``

response:
```json
{
  "code": 200,
  "msg": "18502158803"
}
```

------

#####7.更新手机号

url: account/changeTelephone

method: post

request:
```json
{
  "telephone":"1234567890"
}
```

response:
```json
{
  "code": 200,
  "msg": []
}
```
------
#####8.发送短信

url: account/getMessage

method: post

request:
```json
{
  "telephone":"17621181235"
}
```

response:
```json
{
    "code":200,
    "msg":[]
}
```

-----
#####9.获取原手机号(修改验证手机用)
url: account/getOriginalTelephone

method: get

request: ``{}``

response:
```json
{
  "code":200,
  "msg":"17821161235"
}

```
-----
#####10.更改验证手机
url: account/changeTelephone

method: post

request:
```json
{
  "newTelephone":"17621181235"
}
```

-----

#####11.注册接口(先调用发短信接口)

url: account/register

method: post

request:
```json
{
  "telephone":"17621181235",
  "checkCode":"123456",
  "invitationCode":"333666"

}
```

response:
```json
{
    "code":200,
    "msg":[]
}
```

-----

#####12.登录接口(先调用发短信接口)

url: account/login

method: post

request:
```json
{
  "telephone":"17621181235",
  "checkCode":"123456"

}
```

response:
```json
{
    "code":200,
    "msg":[]
}
```

-----

礼包商城部分

-----

#####1.获取礼包商城全部商品

url: good/getGifts

method: get

request:
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
      "thumbnail": "imgthumb1",//小图
      "name": "礼包商城商品1",
      "headImg": "head",
      "content": "礼包商城商品测试内容",
      "cost": 1,
      "pointValue": 1000,
      "coinValue": 1000,
      "telephone": "123456789"
    },
    {
      "id": 2,
      "thumbnail": "imgthumb2",
      "name": "礼包商城商品2",
      "headImg": "head",
      "content": "礼包商城商品测试内容2",
      "cost": 1,
      "pointValue": 1200,
      "coinValue": 1300,
      "telephone": "1234567891"
    }
  ]
}
```

-----
#####2.获取某个礼包的详细信息

url: good/getGift/:giftId

method: get

request:``{}``

response:
```json
{
  "code": 200,
  "msg": {
    "id": 1,
    "thumbnail": "imgthumb1",
    "name": "礼包商城商品1",
    "headImg": "head",
    "content": "礼包商城商品测试内容",
    "cost": 1,
    "pointValue": 1000,
    "coinValue": 1000,
    "telephone": "123456789"
  }
}
```
-----
#####3.兑换礼包

url: good/exchangeGift/:giftId

method: post

request:``{}``

response:
```json
{
  "code":200,
  "msg":[]
}
```








