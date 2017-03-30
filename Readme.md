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

method:get

request: `{}`

return:
``` javascript
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

