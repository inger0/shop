<%--
  Created by IntelliJ IDEA.
  User: yujingyang
  Date: 2017/4/26
  Time: 下午11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav nav-list">
    <li class="">
        <a href="/admin/dashboard-admin">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 报表 </span>
        </a>

        <b class="arrow"></b>
    </li><!--active open-->

    <li class="" id="manager">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text">
								管理员入口
							</span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="manageUser">
                <a href="/admin/manageUser" >
                    <i class="menu-icon fa fa-caret-right"></i>
                    设置用户
                </a>

            </li>

            <li class="" id="manageGoods">
                <a href="/admin/manageGoods" >
                    <i class="menu-icon fa fa-caret-right"></i>
                    设置首页商品
                </a>

            </li>

            <li class="" id="manageMainPage">
                <a href="/admin/manageMainPage">
                    <i class="menu-icon fa fa-caret-right"></i>
                    首页设置
                </a>

                <b class="arrow"></b>
            </li>



            <li class="" id="manageRate">
                <a href="/admin/manageRate">
                    <i class="menu-icon fa fa-caret-right"></i>
                    参数设置
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="manageClassify">
                <a href="/admin/manageClassify">
                    <i class="menu-icon fa fa-caret-right"></i>
                    类别设置
                </a>

                <b class="arrow"></b>
            </li>

        </ul>
    </li>

    <li class="" id="shop">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-list"></i>
            <span class="menu-text"> 商家入口 </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="manageStore">
                <a href="/admin/shop/manageStore">
                    <i class="menu-icon fa fa-caret-right"></i>
                    设置我的商店
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="uploadGood">
                <a href="/admin/shop/uploadGood">
                    <i class="menu-icon fa fa-caret-right"></i>
                    上传商品
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="manageShopGood">
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    管理商品
                </a>

                <b class="arrow"></b>
            </li>
        </ul>
    </li>






</ul><!-- /.nav-list -->

</body>
</html>
