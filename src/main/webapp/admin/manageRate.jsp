<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../blankup.jsp" %>

<link rel="stylesheet" href="/assets/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="/assets/css/chosen.min.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="/assets/css/daterangepicker.min.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-colorpicker.min.css" />

<%@include file="../blankmiddle.jsp" %>
<!--page content-->
<form class="form-horizontal" action="/adminUser/setRate" method="post" role="form">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="coinRate"> 币与人民币比率 </label>

        <div class="col-sm-9">
            <input type="text" id="coinRate"  name="coinRate" class="col-xs-10 col-sm-5" />
        </div>
    </div>


    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="pointRate"> 分与人民币比率 </label>

        <div class="col-sm-9">
            <input type="text" id="pointRate"  name="pointRate" class="col-xs-10 col-sm-5" />
        </div>
    </div>


    <div class="space-4"></div>


    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="pointGet"> 邀请获得分 </label>

        <div class="col-sm-9">
            <input type="text" id="pointGet"  name="pointGet" class="col-xs-10 col-sm-5" />
        </div>
    </div>


    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="coinGet"> 邀请获得分 </label>

        <div class="col-sm-9">
            <input type="text" id="coinGet"  name="coinGet" class="col-xs-10 col-sm-5" />
        </div>
    </div>
    <div class="space-4"></div>





    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" type="submit">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交
            </button>
        </div>
    </div>

    <div class="hr hr-24"></div>


    <div class="space-24"></div>

</form>



<%@include file="../blankdown.jsp" %>
<!--scripts-->
<script src="/assets/js/dropzone.min.js"></script>


<%@include file="../blankbottom.jsp" %>


<!--inline scripts-->
<script>
    $.ajax({
        url:"/adminUser/getRate",
        type:"get",
        success:function(data){
            var msg = data.msg;
            $("#coinRate").val(msg.coinRate);
            $("#pointRate").val(msg.pointRate);
            $("#coinGet").val(msg.coinGet);
            $("#pointGet").val(msg.pointGet);
        }
    })
</script>
<!--own scripts-->
<script>
    $("#manageRate").addClass("active");
    $("#manager").addClass("active open");

</script>

