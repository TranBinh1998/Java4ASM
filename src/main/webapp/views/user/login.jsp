<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/21/2023
  Time: 2:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <%@ include file="/common/head.jsp"%>
</head>
<body>

<%@ include file="/common/header.jsp"%>

<div class="container-fluid tm-mt-60 ">
    <div class="row tm-mb-50 d-flex justify-content-center">
        <div class="col-lg-4 col-12 mb-5">
            <h2 class="tm-text-primary text-center mb-5">Login</h2>
            <form id="login-form" action="" method="POST" class="tm-contact-form mx-auto">
                <div class="form-group">
                    <input type="text" name="userName" class="form-control rounded-0" placeholder="User Name" required />
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control rounded-0" placeholder="Password" required />
                    <span style="color: red">${errorPassword}</span>
                </div>

                <div class="form-group tm-text-right">
                    <button type="submit" class="btn btn-primary">Đăng Nhập</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>