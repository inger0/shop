<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../blankup.jsp" %>

<link rel="stylesheet" href="/assets/css/jquery-ui.custom.min.css" />

<%@include file="../blankmiddle.jsp" %>
<!--page content-->
<div>
    <form action="/adminUser/uploadMainPageImg" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 首页轮播图1 (925*570) </label>

            <div class="col-sm-9">
                <input type="file" name="file1" id="form-field-1" placeholder="Username" class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 首页轮播图2 (925*570) </label>

            <div class="col-sm-9">
                <input type="file" id="form-field-2" name="file2"  placeholder="Username" class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 首页轮播图2 (925*570) </label>

            <div class="col-sm-9">
                <input type="file" id="form-field-4" name="file3"  placeholder="Username" class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 广告位图 (高280px)</label>

            <div class="col-sm-9">
                <input type="file" id="form-field-3" name="file4" placeholder="Username" class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" type="submit">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交
            </button>
            &nbsp; &nbsp; &nbsp;

        </div>
    </form>

</div>


<%@include file="../blankdown.jsp" %>
<!--scripts-->
<script src="/assets/js/dropzone.min.js"></script>


<%@include file="../blankbottom.jsp" %>


<!--inline scripts-->


<!--own scripts-->

