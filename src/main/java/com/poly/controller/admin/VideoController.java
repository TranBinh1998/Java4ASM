package com.poly.controller.admin;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.VideoService;
import com.poly.service.impl.VideoServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.springframework.util.backoff.FixedBackOff;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VideoControllerOfAdmin",
        value = {"/admin/video",
                "/admin/add-video",
                "/admin/delete",
                "/admin/view-update"
        })

public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 5556655996181160488L;

    private VideoService videoService = new VideoServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        if (currentUser != null && currentUser.getAdmin() == Boolean.TRUE) {

            String action = request.getParameter("action");

            switch (action) {
                case "view":
                    dogetOverView(request, response);
                    break;
                case "delete":
                    doGetDelete(request, response);
                    break;
                case "add":
                    request.setAttribute("isEdit", false);

                    doGetAdd(request, response);
                    break;
                case "edit":
                    request.setAttribute("isEdit", true);
                    doGetEdit(request, response);
                    break;
                default:
                    response.sendRedirect("/admin");
            }

        } else {
            response.sendRedirect("/index");
        }

    }

    private void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id = request.getParameter("id");
            Video video = videoService.findByid(Integer.parseInt(id));
            request.setAttribute("video", video);
            request.getRequestDispatcher("/views/admin/video-edit.jsp").forward(request, response);
    }

    private void doGetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/video-edit.jsp").forward(request, response);
    }

    private void doGetDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String id = request.getParameter("id");

        Video video = videoService.findByid(Integer.valueOf(id));

        String href = video.getHref();

        Video videoDeleted = videoService.delete(href);

        if (videoDeleted != null) {
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }


    }


    private void dogetOverView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Video> videos = videoService.finAll();
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/views/admin/video-overview.jsp").forward(request, response);

    }

    // link sẽ có dạng : http://localhost:8080/admin/video?action=view
    // link sẽ có dạng : http://localhost:8080/admin/video?action=edit&href={href}
    // link sẽ có dạng : http://localhost:8080/admin/video?action=add
    // link sẽ có dạng : http://localhost:8080/admin/video?action=delete&href={href}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        if (currentUser != null && currentUser.getAdmin() == Boolean.TRUE) {

            String action = request.getParameter("action");

            switch (action) {

                case "add":
                    doPostAdd(request, response);
                    break;
                case "edit":
                    doPostEdit(request, response);
                    break;
                default:
                    response.sendRedirect("/admin");
            }

        } else {
            response.sendRedirect("/index");
        }

    }

    private void doPostEdit(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String title = request.getParameter("title");
        String href = request.getParameter("href");
        String hrefOrigin = request.getParameter("hrefOrigin");
        String description = request.getParameter("description");
        String poster = request.getParameter("poster");
        Video video = videoService.findByHref(hrefOrigin);
                // Xet thong so moi
        video.setTitle(title);
        video.setDescription(description);
        video.setHref(href);
        video.setPoster(poster);

        Video videoReturn = videoService.update(video);
        if (videoReturn!= null) {
            response.setStatus(204);
        }else {
            response.setStatus(400);
        }


    }

    private void doPostAdd(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String title = request.getParameter("title");
        String href = request.getParameter("href");
        System.out.println(href);
        String description = request.getParameter("description");
        String poster = request.getParameter("poster");
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setHref(href);
        video.setPoster(poster);

        Video videoReturn = videoService.create(video);
        if (videoReturn!= null) {
            response.setStatus(204);
        }else {
            response.setStatus(400);
        }


    }
}
