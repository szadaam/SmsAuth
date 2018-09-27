<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="db" class="network.Database" scope="session"/>
<jsp:scriptlet>
    String tel = request.getParameter("tel");
    String code = request.getParameter("code");
    String result = db.authenticate(tel, code);
    if(result.equals("success")) {
        session.setAttribute("logged", "in");
        session.setAttribute("tel", tel);
        session.setAttribute("code", code);
    }
    out.print(result);
</jsp:scriptlet>