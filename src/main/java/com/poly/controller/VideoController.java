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

@WebServlet(urlPatterns = "/video")
public class VideoController extends HttpServlet {

    private static final long serialVersionUID = -1295061815378965734L;

    private VideoService videoService = new VideoServiceImpl();

    private HistoryService historyService = new HistoryServiceImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        String id;
        id = request.getParameter("id");
        String href;
        Video video = videoService.findByid(Integer.parseInt(id));
        href = video.getHref();
        Integer videoId = video.getId();
        HttpSession session = request.getSession();
        switch (actionParam) {
            case "watch" : {
                doGetWatch(session,href, request, response);
            }
            break;
            case "like" : {
                doGetLike(session,videoId, request, response);
            }
            break;
        }

    }
    private void doGetLike(HttpSession session, Integer id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
       // Lấy user đang đăng nhập thông qua session
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        boolean result = historyService.updateLikeOrUnlikeForFindById(currentUser,  id);
        if (result == true) {
            response.setStatus(204); // Thành công nhưng không trả về data
        }else {
            response.setStatus(400);
        }

     }


    private void  doGetWatch(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("Href2"+href);
        Video video = videoService.findByHref(href);
        request.setAttribute("video", video);
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);

        if (currentUser != null) {
            History history = historyService.create(currentUser, video);
            request.setAttribute("flagLikedBtn", history.getLiked());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/video-detail.jsp");
            requestDispatcher.forward(request, response);
        }else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/video-detail.jsp");
            requestDispatcher.forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
