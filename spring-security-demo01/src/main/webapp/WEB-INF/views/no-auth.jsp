<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("PATH", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>权限控制</title>
    <link rel="stylesheet" href="${PATH }/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部导航 -->
    <%@include file="/WEB-INF/include/navbar.jsp" %>

    <!-- 侧边栏 -->
    <%@include file="/WEB-INF/include/sidebar.jsp" %>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
           <div style="padding: 20px;">
               <p>抱歉！您没有权限查看该数据！</p>
           </div>
        </div>
    </div>
    <div class="layui-footer"></div>
</div>
<script src="${PATH }/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function() {
        var element = layui.element;

    });
</script>
</body>
</html>
