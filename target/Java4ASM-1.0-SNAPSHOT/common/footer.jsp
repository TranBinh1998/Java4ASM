<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/21/2023
  Time: 11:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="tm-bg-gray pt-5 pb-3 tm-text-gray tm-footer">
    <div class="container-fluid tm-container-small">
        <div class="row">
            <div class="col-lg-6 col-md-12 col-12 px-5 mb-5">
                <h3 class="tm-text-primary mb-4 tm-footer-title">About Catalog-Z</h3>
                <p>Catalog-Z is free <a rel="sponsored" href="https://v5.getbootstrap.com/">Bootstrap 5</a> Alpha 2 HTML Template for video and photo websites. You can freely use this TemplateMo layout for a front-end integration with any kind of CMS website.</p>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-12 px-5 mb-5">
                <h3 class="tm-text-primary mb-4 tm-footer-title">Our Links</h3>
                <ul class="tm-footer-links pl-0">
                    <li><a href="#">Advertise</a></li>
                    <li><a href="#">Support</a></li>
                    <li><a href="#">Our Company</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-12 px-5 mb-5">
                <ul class="tm-social-links d-flex justify-content-end pl-0 mb-5">
                    <li class="mb-2"><a href="https://facebook.com"><i class="fab fa-facebook"></i></a></li>
                    <li class="mb-2"><a href="https://twitter.com"><i class="fab fa-twitter"></i></a></li>
                    <li class="mb-2"><a href="https://instagram.com"><i class="fab fa-instagram"></i></a></li>
                    <li class="mb-2"><a href="https://pinterest.com"><i class="fab fa-pinterest"></i></a></li>
                </ul>
                <a href="#" class="tm-text-gray text-right d-block mb-2">Terms of Use</a>
                <a href="#" class="tm-text-gray text-right d-block">Privacy Policy</a>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-7 col-12 px-5 mb-3">
                Copyright 2020 Catalog-Z Company. All rights reserved.
            </div>
            <div class="col-lg-4 col-md-5 col-12 px-5 text-right">
                Designed by <a href="https://templatemo.com" class="tm-text-gray" rel="sponsored" target="_parent">TemplateMo</a>
            </div>
        </div>
    </div>
</footer>

<script>
    var userNameInput = document.querySelector("input[name='userName']");
    var passwordInput = document.querySelector("input[name='password']");
    var configPasswordInput = document.querySelector("input[name='cfmpassword']");
    var messageDiv = document.querySelector("#password-message");
    var messageUser = document.querySelector("#userName-message");
    var buttonSubmit = document.querySelector("#button-submit");


    function checkPasswordMatch() { // Lấy giá trị của password và configpassword
        var passwordValue = passwordInput.value;
        var configPasswordValue = configPasswordInput.value;
        // So sánh hai giá trị này
        // Nếu bằng nhau, trả về true
        if (passwordValue === configPasswordValue) {
            return true;
        }
        // Nếu khác nhau, trả về false
        else {
            return false;
        }

    }

    function checkUserLength() {
        var  userNameLength = userNameInput.value.length;
        if (userNameLength <= 10 ) {
            return true;
        }else {
            return  false;
        }
    }

    // Định nghĩa hàm xử lý sự kiện gửi form
    function handleSubmitForm() {
        // Gọi hàm kiểm tra password
        var passwordMatch = checkPasswordMatch();
        var userMacth = checkUserLength();

        if (userMacth === true) {

        } else {
            messageUser.innerHTML = "Độ dài tên đăng nhập không quá 10 ký tự";
            messageUser.style.color = "red";
            return false;
        }


        if (passwordMatch) {
            // Nếu password trùng nhau, thông báo thành công và cho phép gửi form
            messageDiv.innerHTML = "Password matched!"; // Thay đổi nội dung của thẻ div thành thông báo khớp mật khẩu
            messageDiv.style.color = "green"; // Thay đổi màu chữ của thẻ div thành màu xanh lá
            return true;
        }
        // Nếu password không trùng nhau, thông báo lỗi và ngăn chặn gửi form
        else {
            messageDiv.innerHTML = "Password not matched!"; // Thay đổi nội dung của thẻ div thành thông báo không khớp mật khẩu
            messageDiv.style.color = "red"; // Thay đổi màu chữ của thẻ div thành màu đỏ
            return false;
        }
    } // Đóng ngoặc nhọn cho hàm

    document.querySelector("#register-form").onsubmit = handleSubmitForm;

</script>
<script src="<c:url value ='/templates/user/js/plugins.js'/>"></script>
<script>
    $(window).on("load", function() {
        $('body').addClass('loaded');
    });
</script>
<script>
    $('#changePassBtn').click(function () {
        $('#messageChangePass').text('');
        var currentPass = $('#curentPass').val();
        var newPass = $('#newPass').val();  // Lấy giá trị ở  thẻ có id -

        var formData = {
            'currentPass' : currentPass, // set giá trị để controller lấy giá trị
            'newPass' : newPass
        };
        $.ajax({
            url : 'changePass',
            type : 'POST',
            data : formData
        }).then(function (data) {
            $('#messageChangePass').text('Your password has been changed sucssessfully');
        }).fail(function (error) {
            $('#messageChangePass').text('You password is not correct, try again');
        });
    });

</script>