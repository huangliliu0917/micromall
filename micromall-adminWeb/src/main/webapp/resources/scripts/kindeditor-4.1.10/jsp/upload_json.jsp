<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="com.micromall.datacenter.utils.ResourceServer" %>
<%

    /**
     * KindEditor JSP
     *
     * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
     * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
     *
     */

//文件保存目录路径
    ResourceServer resourceServer = (ResourceServer) request.getServletContext().getAttribute("uploadResourceServer");

//定义允许上传的文件扩展名
    HashMap<String, String> extMap = new HashMap<String, String>();
    extMap.put("image", "gif,jpg,jpeg,png,bmp");
    extMap.put("flash", "swf,flv");
    extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

    response.setContentType("text/html; charset=UTF-8");
//创建文件夹


    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setHeaderEncoding("UTF-8");
    List items = upload.parseRequest(request);
    Iterator itr = items.iterator();
    while (itr.hasNext()) {
        FileItem item = (FileItem) itr.next();
        if (!item.isFormField()) {
            String path = resourceServer.saveResource(item.getInputStream(), item.getName(), 0);

            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", resourceServer.resourceUri(path));
            response.getWriter().println(obj.toJSONString());
//            out.println();
        }
    }
%>
<%!
    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
%>