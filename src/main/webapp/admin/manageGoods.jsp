<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../blankup.jsp" %>

<link rel="stylesheet" href="/assets/css/jquery-ui.custom.min.css" />

<%@include file="../blankmiddle.jsp" %>
<!--page content-->
<div>
    <form action="/adminUser/uploadMainPageImg" method="post"  class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="coinRate"> 币与人民币比值(1币=?人民币) </label>

            <div class="col-sm-9">
                <input type="text" name="coinRate" id="coinRate" class="col-xs-10 col-sm-5" />
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="pointRate">  分与人民币比值(1币=?人民币) </label>

            <div class="col-sm-9">
                <input type="text" id="pointRate" name="pointRate"  class="col-xs-10 col-sm-5" />
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
<script>
    $.ajax({
        url:""
    })
</script>

<!--own scripts-->

