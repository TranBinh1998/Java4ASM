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
    <title>Register</title>

    <%@ include file="/common/head.jsp"%>
</head>
<body>

<%@ include file="/common/header.jsp"%>

<div class="container-fluid tm-mt-60 ">
    <div class="row tm-mb-50 d-flex justify-content-center">
        <div class="col-lg-4 col-12 mb-5">
            <h2 class="tm-text-primary text-center mb-5">Login</h2>
            <h5 class="text-warning">${registerWarning}</h5>
            <form id="register-form" action="register" method="POST" class="tm-contact-form mx-auto">
                <div class="form-group">
                    <input type="text" name="userName" class="form-control rounded-0" placeholder="User Name" required />
                    <span id="userName-message" class="text-warning"> ${userNameWarning}</span>
                </div>
                <div class="form-group">
                    <input id="new_password" type="password" name="password" class="form-control rounded-0" placeholder="Password" required />

                </div>
                <div class="form-group">
                    <input id="verifyPassword" type="password" name="cfmpassword" class="form-control rounded-0" placeholder="Confirm Password" required />
                 </div>
                <span id="password-message"></span>
                <div class="form-group">
                    <input type="email" name="email" class="form-control rounded-0" placeholder="Email" required />
                    <span class="text-warning">${emailWarning}</span>
                </div>
                <div class="form-group tm-text-right">
                    <button id="button-submit" type="submit"  class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/common/footer.jsp"%>
</body>
</html>