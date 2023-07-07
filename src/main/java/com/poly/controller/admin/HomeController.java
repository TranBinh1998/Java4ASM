package com.poly.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.constant.SessionAttr;
import com.poly.dto.VideoLikedInfo;
import com.poly.entity.User;
import com.poly.service.StatsService;
import com.poly.service.UserSevice;
import com.poly.service.impl.StatsServiceImpl;
import com.poly.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HomeControllerOfAdmin",
            value = {"/admin",
                    "/favoritesAdmin"})
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = -3115541213981353657L;

    private StatsService statsService = new StatsServiceImpl();

    private UserSevice userSevice = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);

        if (currentUser != null && currentUser.getAdmin() == Boolean.TRUE) {
            String path = request.getServletPath();
            if (path.contains("admin")) {
                doGetHome(request,response);
            }else if (path.contains("favoritesAdmin")) {
                doGetFavorites(request, response);
            }
         }else {
            response.sendRedirect("/index");
        }

    }

    private void doGetFavorites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out  = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String videoHref = request.getParameter("href");

        List<User> listUser = userSevice.findUserLikedByVideoHref(videoHref);

        if (listUser.isEmpty()) {
            response.setStatus(400);
        }else {
            ObjectMapper mapper = new ObjectMapper();
            String dataResponse = mapper.writeValueAsString(listUser);
            // Có trả về data thì trả về 200
            response.setStatus(200);
            out.print(dataResponse);
            out.flush();
        }



    }

    private void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VideoLikedInfo> videoLikedInfos = statsService.findVideoLikedInfo();
        request.setAttribute("videos", videoLikedInfos);
        request.getRequestDispatcher("/views/admin/home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
