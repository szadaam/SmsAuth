<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/><jsp:scriptlet>request.setCharacterEncoding("ISO-8859-2");</jsp:scriptlet><jsp:include page="includes/head.jsp"/><jsp:useBean id="db" class="network.Database" scope="session"/>
        <div class="container">
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <h1>Bejelentkezve <%=session.getAttribute("tel")%></h1>
                    <a href="logout.jsp">
                        <button class="btn btn-primary btn-lg">Kijelentkezés</button>
                    </a>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-dark">
                        <thead>
                          <tr>
                            <th scope="col">#</th>
                            <th scope="col">tel</th>
                            <th scope="col">code</th>
                          </tr>
                        </thead>
                        <tbody><%=db.list_users()%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
<jsp:include page="includes/footer.jsp"/>
<%

if(session.getAttribute("logged").equals("out")) {
    response.sendRedirect("index.jsp");
}

%>