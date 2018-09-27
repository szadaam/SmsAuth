<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/><jsp:scriptlet>request.setCharacterEncoding("ISO-8859-2");</jsp:scriptlet><jsp:include page="includes/head.jsp"/>
<link rel="stylesheet" href="css/index.css">
        <div class="container">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form class="form-signin" action="login.jsp" method="post">
                        <div class="form-label-group">
                            <input name="telephone" class="form-control" type="tel" placeholder="Telefonszám" required autofocus>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block">SMS Kód igénylése</button>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
<jsp:include page="includes/footer.jsp"/>
<%

if(session.getAttribute("logged") == null){
    session.setAttribute("logged", "out");
}

if(session.getAttribute("logged").equals("in")) {
    response.sendRedirect("loggedin.jsp");
}

%>


