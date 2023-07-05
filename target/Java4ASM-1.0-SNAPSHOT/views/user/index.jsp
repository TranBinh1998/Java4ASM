<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/21/2023
  Time: 2:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phim Mới</title>

    <%@ include file="/common/head.jsp" %>
</head>
<body>

<%@ include file="/common/header.jsp" %>
<div class="container-fluid tm-container-content tm-mt-60">
    <div class="row mb-4">
        <h2 class="col-6 tm-text-primary">
            List Firm
        </h2>
        <div class="col-6 d-flex justify-content-end align-items-center">
            <form action="" class="tm-text-primary">
                Page <input type="text" value="1" size="1" class="tm-input-paging tm-text-primary"> of 200
            </form>
        </div>
    </div>
    <div class="row tm-mb-90 tm-gallery">
        <c:forEach items="${videos}" var="video">
            <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
                <figure class="effect-ming tm-video-item">
                    <img src="${video.poster}" alt="Image" class="img-fluid">
                    <figcaption class="d-flex align-items-center justify-content-center">
                        <h2>ViewMore</h2>
                        <a href="<c:url value='/video?action=watch&id=${video.id}'/>">View more</a>
                    </figcaption>

                </figure>
                <h5 class="tm-text-secondary" style="white-space: nowrap; overflow: hidden">
                        ${video.title}

                </h5>
                <div class="d-flex justify-content-between tm-text-gray">
                    <span class="tm-text-gray-light">${video.shares}  Shares</span>
                    <span>${video.views}  view</span>
                </div>
            </div>
        </c:forEach>

    </div> <!-- row -->
    <div class="row tm-mb-90">
        <div class="col-12 d-flex justify-content-between align-items-center tm-paging-col">
           <c:if test="${currentPage == 1}">
               <a href="" class="btn btn-primary tm-btn-prev mb-2 disabled">Previous</a>
           </c:if>
            <c:if test="${currentPage > 1}" >
                <a href="index?page=${currentPage - 1}" class="btn btn-primary tm-btn-prev mb-2 ">Previous</a>
            </c:if>
            <div class="tm-paging d-flex">
                <c:forEach varStatus="i" begin="1" end="${maxPage}">
                    <a href="index?page=${i.index}"
                       class=" ${currentPage == i.index ? 'active' : ''} tm-paging-link">${i.index}</a>
                </c:forEach>
            </div>
            <c:if test="${currentPage == maxPage}">
                <a href="javascript:void(0);" class="btn btn-primary tm-btn-next disabled" >Next Page</a>
            </c:if>
            <c:if test="${currentPage < maxPage}" >
                <a href="index?page=${currentPage + 1}" class="btn btn-primary tm-btn-next ">NextPage</a>
            </c:if>



        </div>
    </div>
</div> <!-- container-fluid, tm-container-content -->
<%@ include file="/common/footer.jsp" %>
</body>
</html>