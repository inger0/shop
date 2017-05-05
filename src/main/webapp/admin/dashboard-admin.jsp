<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../blankup.jsp" %>

<%@include file="../blankmiddle.jsp" %>
<!--page content-->
<div class="row">
    <div class="col-sm-6">
        <div class="widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">邀请关系网查看</h4>
            </div>

            <div class="widget-body">
                <div class="widget-main padding-8">
                    <ul id="tree1"></ul>
                </div>
            </div>
        </div>
    </div>

</div>

<%@include file="../blankdown.jsp" %>

<!--scripts-->
<script src="/assets/js/tree.min.js"></script>

<%@include file="../blankbottom.jsp" %>
<script type="text/javascript">

    $.ajax({
        type: "get",
        url: "/adminUser/getUserTree",
        success: function (data) {
            var sampleData = initiateDemoData();//see below


            $('#tree1').ace_tree({
                dataSource: sampleData['dataSource1'],
                multiSelect: true,
                cacheItems: true,
                'open-icon': 'ace-icon tree-minus',
                'close-icon': 'ace-icon tree-plus',
                'itemSelect': true,
                'folderSelect': false,
                'selected-icon': 'ace-icon fa fa-check',
                'unselected-icon': 'ace-icon fa fa-times',
                loadingHTML: '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>'
            });

            /**
             //Use something like this to reload data
             $('#tree1').find("li:not([data-template])").remove();
             $('#tree1').tree('render');
             */


            /**
             //please refer to docs for more info
             $('#tree1')
             .on('loaded.fu.tree', function(e) {
	})
             .on('updated.fu.tree', function(e, result) {
	})
             .on('selected.fu.tree', function(e) {
	})
             .on('deselected.fu.tree', function(e) {
	})
             .on('opened.fu.tree', function(e) {
	})
             .on('closed.fu.tree', function(e) {
	});
             */



            function initiateDemoData() {

                var tree_data = data.msg;


                var dataSource1 = function (options, callback) {
                    var $data = null
                    if (!("text" in options) && !("type" in options)) {
                        $data = tree_data;//the root tree
                        callback({data: $data});
                        return;
                    }
                    else if ("type" in options && options.type == "folder") {
                        if ("additionalParameters" in options && "children" in options.additionalParameters)
                            $data = options.additionalParameters.children || {};
                        else $data = {}//no data
                    }

                    if ($data != null)//this setTimeout is only for mimicking some random delay
                        setTimeout(function () {
                            callback({data: $data});
                        }, parseInt(Math.random() * 500) + 200);

                    //we have used static data here
                    //but you can retrieve your data dynamically from a server using ajax call
                    //checkout examples/treeview.html and examples/treeview.js for more info
                }

                return {'dataSource1': dataSource1}


            }

        }
    })

</script>

