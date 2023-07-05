
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
    <title>${video.title}</title>

    <%@ include file="/common/head.jsp"%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>

<%@ include file="/common/header.jsp"%>

<div class="container-fluid tm-container-content tm-mt-60">
    <div class="row mb-4">
        <h2 class="col-12 tm-text-primary">${video.title}</h2>
    </div>
    <div class="row tm-mb-90 ">
<%--        d-flex justify-content-center --%>
        <div class="col-xl-8 col-lg-7 col-md-6 col-sm-12">
            <iframe style="height: 36em;" id="tm-video" src="${video.href}" allowfullscreen>
            </iframe>
        </div>
        <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">
            <div class="tm-bg-gray tm-video-details">
                <c:if test="${not empty sessionScope.currentUser}" >
                    <div class="text-center mb-5">
                        <button id="likeOrUnlikeBtn" class="btn btn-primary tm-btn-big">
                            <c:choose>
                            <c:when test="${flagLikedBtn == true}">
                                UnLike
                            </c:when>
                            <c:otherwise>
                                Like
                            </c:otherwise>
                        </c:choose>
                        </button>
                    </div>
                </c:if>
                    <div class="text-center mb-5">
                        <a href="#" class="btn btn-primary tm-btn-big">Share</a>
                    </div>

                <div class="mb-4">
                    <h3 class="tm-text-gray-dark mb-3">Description</h3>
                    <p>${video.description}</p>
                </div>
                <input id="videoIdHdn" type="hidden" value="${video.id}">
            </div>
        </div>
    </div>
</div> <!-- container-fluid, tm-container-content -->
<script>
    //     $('#likeOrUnlikeBtn').click(function () {
    //         const videoId = $('#videoIdHdn').val();
    //         $.ajax({
    //     url: 'video?action=like&id='+videoId
    // }).then(function(data){
    //     const text = $('#likeOrUnlikeBtn').text();
    //     if (text.indexOf('Like') > 0) {
    //     $('#likeOrUnlikeBtn').text('UnLike');
    // }else {
    //     $('#likeOrUnlikeBtn').text('Like');
    // }
    // }).fail(function (error) {
    //     alert('Thất bại. Xin ấn lại')
    // });
    // });
    $(document).ready(function () {
        $('#likeOrUnlikeBtn').on('click', function () {
            const videoId = $('#videoIdHdn').val();
            $.ajax({
                url: 'video?action=like&id=' + videoId
            }).done(function (data) {
                const text = $('#likeOrUnlikeBtn').text();
                if (text.includes('UnLike')) {
                    $('#likeOrUnlikeBtn').text('Like');
                } else {
                    $('#likeOrUnlikeBtn').text('UnLike');
                }
            }).fail(function (error) {
                alert('Thất bại. Xin ấn lại')
            });
        });
    });
</script>

<%@ include file="/common/footer.jsp"%>
</body>
</html>