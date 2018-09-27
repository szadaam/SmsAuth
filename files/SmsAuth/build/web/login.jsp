<jsp:include page="includes/head.jsp"/><jsp:useBean id="sms" class="network.SMS" scope="session"/><%String tel = request.getParameter("telephone");String sms_result = sms.send(tel);%>
            <link rel="stylesheet" href="css/index.css">
            <div class="container">
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <h3><%=sms_result%></h3>
                        <form id="login" class="form-signin">
                            <div class="form-label-group">
                                <input id="code" class="form-control" type="text" placeholder="SMS Kód" required autofocus>
                            </div>
                            <div class="form-group">
                                <input id="tel" class="form-control" type="text" value="<%=tel%>" disabled>
                            </div>
                            <button class="btn btn-lg btn-primary btn-block">Bejelentkezés</button>
                        </form>
                        <a href="index.jsp">Vissza</a>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
<jsp:include page="includes/footer.jsp"/>
