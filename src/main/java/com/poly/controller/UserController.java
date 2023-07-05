package com.poly.controller;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.service.EmailService;
import com.poly.service.UserSevice;
import com.poly.service.impl.EmailServiceImpl;
import com.poly.service.impl.UserServiceImpl;
import com.poly.util.HashUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.tags.shaded.org.apache.bcel.generic.IF_ACMPEQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "UserController", value = {
        "/login",
        "/logout",
        "/register",
        "/changePass"
})
public class UserController extends HttpServlet {

    // Trỏ chuột vào tên lớp severlet để tạo

    private static final long serialVersionUID = 7799570694017823316L;

    private UserSevice userSevice = new UserServiceImpl();

    private EmailService emailService = new EmailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        String uri = request.getRequestURI(); // C1
        String path = request.getServletPath(); // Sẽ lấy đuôi
        // Dùng if else  hoặc switchcase để tìm đúng uri
        switch (path) {
            case "/login": {
                doGetLogin(request, response);
            }
            break;
            case "/register": {
                doGetRegister(request, response);
            }
            break;
            case "/logout": {
                doGetLogout(session, request, response);
            }

        }


    }

    private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.removeAttribute(SessionAttr.CURRENT_USER);
        response.sendRedirect("/index");
    }

    private void doGetRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/register.jsp");
        requestDispatcher.forward(request, response);
//        response.sendRedirect("/index");
    }


    private void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/user/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        switch (path) {
            case "/login": {
                doPostLogin(session, request, response);
            }
            break;
            case "/register": {
                doPostRegister(session, request, response);
            }
            break;
            case "/forgotPass": {
                doPostForgotPass(request, response);
            }
            break;
            case "/changePass": {
                doPostchangePass(session, request, response);
            }

        }
    }

    // Hàm kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Kiểm tra đầu vào input

        if (userName == null || password == null || email == null) {
            request.setAttribute("registerWarning", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
            return;
        }
        // Kiểm tra định dạng email
        if (isValidEmail(email) == false) {
            request.setAttribute("emailWarning", "Email không hợp lệ");
            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
            return;
        }
        // Check if user name or email already exitsts
        // Kiểm tra xem người dùng hoặc email đã tồn tại hay chưa
        User existingUser = userSevice.findByUserName(userName);
        if (existingUser != null) {
            // Nếu đã tồn tại user trong database thì set Atribute
            request.setAttribute("userNameWarning", "Tên đăng nhập đã tồn tại");

            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra xem có email này tồn tại trong daata base hay không
        existingUser = userSevice.findByEmail(email);

        if (existingUser != null) {
            request.setAttribute("emailWarning", "Email đã được đăng ký");
            request.getRequestDispatcher("views/user/register.jsp").forward(request, response);
            return;
        }

        // Mã hóa pass word

        password = Base64.getEncoder().encodeToString(HashUtil.hash(password));

        // Register new user (Đăng kí mới 1 user)

        User newUser = userSevice.register(userName, password, email);

        if (newUser != null) {
            // Registrantion successful
            // Send welcome email


            // Set current user ín sessiom
            session.setAttribute(SessionAttr.CURRENT_USER, newUser);

            response.sendRedirect("/index");

        } else {
            request.setAttribute("registerWarning", "Lỗi đăng ký");
            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);


        }


    }

    private void doPostchangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");


        String currentPass = request.getParameter("currentPass");
        String newPass = request.getParameter("newPass");


        System.out.println(currentPass);

        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);

        System.out.println("ten user");
        System.out.println(currentUser.getUserName());

        if (currentUser.getPassword().equals(currentPass)) {

            currentUser.setPassword(newPass);

            User updateUser = userSevice.update(currentUser);
            if (updateUser != null) {
                session.setAttribute(SessionAttr.CURRENT_USER, updateUser);
                response.setStatus(204);
            } else {
                response.setStatus(400);
            }
        } else {
            response.setStatus(400);
        }

    }


    private void doPostForgotPass(HttpServletRequest request, HttpServletResponse response) {
    }

//    private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String userName = request.getParameter("userName");
//
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        String[] checkEmail = email.split("@");
//
//        String nameEmail = checkEmail[0];
//        // Xử lí thì làm bên service còn controller thì chỉ gọi và trả ra kết quả
//        List<User> userList = new ArrayList<>();
//
//
//
//        userList = userSevice.findAll();
//
//        int checkUser = 0;
//        int checkEmailByFor = 0;
//        String emailAfterSplit;
//        String[] emailSplit;
//        String emailBeforSplit;
//        for (User user : userList) {
//            emailAfterSplit = user.getEmail();
//            emailSplit = emailAfterSplit.split("@");
//            emailBeforSplit = emailSplit[0];
//            if (user.getUserName().equalsIgnoreCase(userName)) {
//                checkUser = 1;
//            }
//            // Tối ưu hơn thì cắt phần trước @ rồi mới đi so sánh
//            if (user.getEmail().equalsIgnoreCase(emailBeforSplit)) {
//                checkEmailByFor = 1;
//            }
//        }
//        if (checkUser == 1) {
//            request.setAttribute("userNameWarring", "Tên đăng nhập đã tồn tại");
//            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
//        } else if (checkEmailByFor == 1) {
//            request.setAttribute("emailWarring", "Email đã được đăng ký");
//            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
//        } else {
//            User user = userSevice.register(userName, password, email);
//            if (user != null) {
//                // Phần gửi email hỏng
//                // Tao xong user thi gui 1 email
////                emailService.sendEmail(getServletContext(), user, "wellcome");
//
//                session.setAttribute(SessionAttr.CURRENT_USER, user);
//                // Khi đăng kí đăng nhập đều lưu vào sesion nên khi đăng xuất chỉ cần clear sesion
//
//                response.sendRedirect("/index");
//            } else {
//                request.setAttribute("registerlWarring", "Lỗi đăng kí");
//                request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
//            }
//
//        }
//
//
//    }

    private void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        password = Base64.getEncoder().encodeToString(HashUtil.hash(password));

        User user = userSevice.login(userName, password);
        // Đăng nhập thất bại user = null
        // Nếu đăng nhập thành công thì user này khác null lưu vào session trả về trang index
        if (user != null) {
            session.setAttribute(SessionAttr.CURRENT_USER, user);
            response.sendRedirect("/index");
        } else {
            request.setAttribute("errorPassword", "Tên đăng nhập hoặc mật khẩu không đúng");
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        }
    }
}
