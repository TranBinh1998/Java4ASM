package com.poly.controller;

import com.poly.constant.SessionAttr;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;
import com.poly.service.impl.HistoryServiceImpl;
import com.poly.service.impl.VideoServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {
        "/index",
        "/favorites",
        "/history"
})
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = -843652978187999395L;

    private static final int VIDEO_MAX_PAGE_SIZE = 2;


    private VideoService videoService = new VideoServiceImpl();

    private HistoryService historyService = new HistoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        switch (path) {
            case "/index": {
                doGetIndex(request, response);
            }
            break;
            case "/favorites": {
                doGetFavorites(session, request, response);
            }
            break;
            case "/history": {
                doGetHistory(session, request, response);
            }
            break;
            default:
                doGetIndex(request, response);

        }

    }

    private void doGetHistory(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);

        List<History> historyList = historyService.findByUser(user.getUserName());

        List<Video> videos = new ArrayList<>();
        historyList.forEach(history -> {
            videos.add(history.getVideo());
        });
        request.setAttribute("videos", videos);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/user/history.jsp");
        requestDispatcher.forward(request, response);

    }

    private void doGetFavorites(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        // User được lấy từ session. Những ng đã đăng nhập
        List<History> histories = historyService.findByUserAndIsLiked(user.getUserName());
        List<Video> videos = new ArrayList<>();
        histories.forEach(item -> {
            videos.add(item.getVideo());
        });
        request.setAttribute("videos", videos);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/favorites.jsp");
        requestDispatcher.forward(request, response);

        /*


         */

    }

    // Phân trang sẽ có dạng  :  localhost:8080/asm-java4/index?page={}
    // Xét số video mỗi trang
    private void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Video> countVideo = videoService.finAll();

        int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
        request.setAttribute("maxPage", maxPage);

        String pageNumber = request.getParameter("page");
        List<Video> videos;

        if (pageNumber == null) {
            videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
            System.out.println(videos.size());
            request.setAttribute("currentPage", 1);
        } else {
            videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currentPage", pageNumber);
        }


        request.setAttribute("videos", videos);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
