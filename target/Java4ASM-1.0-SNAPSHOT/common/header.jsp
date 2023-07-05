<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/21/2023
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Page Loader -->
<div id="loader-wrapper">
    <div id="loader"></div>

    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>

</div>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a style="color: #1d2124;"  class="navbar-brand" href="/index">
            <i class="fas fa-film mr-2"></i>
            Phim moi
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <li class="nav-item">
                            <a class="nav-link nav-link-4 active " aria-current="page" data-toggle="modal" data-target="#changePassModal"  >WellCome,${sessionScope.currentUser.userName}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4 " href="/favorites">Phim yêu thích</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4 " href="/history">Xem gần đây</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4 " href="/logout">Đăng xuất</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link nav-link-2" href="/login">Đăng nhập</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-3" href="/register">Đăng kí</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4" href="contact.html">Quên mật khẩu</a>
                        </li>
                    </c:otherwise>

                </c:choose>



            </ul>
        </div>
    </div>
</nav>

<div class="tm-hero d-flex justify-content-center align-items-center" data-parallax="scroll"
     data-image-src="<c:url value= '/templates/user/img/hero.jpg'/>">
    <form class="d-flex tm-search-form">
        <input class="form-control tm-search-input" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success tm-search-btn" type="submit">
            <i class="fas fa-search"></i>
        </button>
    </form>
</div>

<!-- Modal -->
<div class="modal fade" id="changePassModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="password" name="currentPass" id="curentPass" class="form-control rounded-0" placeholder="Password" required />
                </div>
                <div class="form-group">
                    <input type="password" name="newPass" id="newPass" class="form-control rounded-0" placeholder="New Password" required />
                </div>
                <div class="form-group">
                    <input type="password" name="confirmPassword" class="form-control rounded-0" placeholder="Confirm Password" required />
                </div>
                <h5 style="color: red" id="messageChangePass"></h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="changePassBtn">Save changes</button>
            </div>
        </div>
    </div>
</div>

