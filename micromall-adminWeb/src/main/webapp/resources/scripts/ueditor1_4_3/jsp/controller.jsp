<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         import="com.baidu.ueditor.ActionEnter"
         pageEncoding="UTF-8" %>
<%@ page import="org.springframework.core.env.Environment" %>
<%@ page import="com.micromall.datacenter.utils.ResourceServer" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html");

    String rootPath = application.getRealPath("/");
    ResourceServer resourceServer = (ResourceServer) request.getServletContext().getAttribute("uploadResourceServer");
    rootPath = resourceServer.getServerUri();
    //out.write(new ActionEnter(request, rootPath).exec());
%>