<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../blankup.jsp" %>

<link rel="stylesheet" href="/assets/css/jquery-ui.custom.min.css" />

<%@include file="../../blankmiddle.jsp" %>
<!--page content-->
<div>
    <form action="/adminUser/uploadShopImg" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">

        <div class="space-4"></div>


        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="shopName"> 商店名称 </label>

            <div class="col-sm-9">
                <input type="text" name="shopName" id="shopName"  class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>


        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="telephone"> 客服电话 </label>

            <div class="col-sm-9">
                <input type="text" name="telephone" id="telephone"  class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 商店头图 </label>

            <div class="col-sm-9">
                <input type="file" name="file1" id="form-field-1"  class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 商店活动图</label>

            <div class="col-sm-9">
                <input type="file" id="form-field-2" name="file2"  placeholder="Username" class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>



        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" type="submit">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交
            </button>
            &nbsp; &nbsp; &nbsp;

        </div>
    </form>

</div>


<%@include file="../../blankdown.jsp" %>
<!--scripts-->


<%@include file="../../blankbottom.jsp" %>


<!--inline scripts-->


<!--own scripts-->

