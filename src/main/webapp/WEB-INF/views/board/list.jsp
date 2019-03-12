<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="resources/css/board_list.css" rel="stylesheet" />
<link href="resources/css/write_view.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu_bar.jsp" flush="false"/>
<div class="board_box">
	<div class=title_box>
		<a href="list" class=Main_title>자유게시판</a>
	</div>
	<div class=board_find_write>
	    <div class="board_find">
	        <form method="get" action="list">
	        	<input type="text" name="text_find" value="${text_find}">
	        	<span class="submit">
	        		<input type="submit" value="검색">
	        	</span>
	        </form>
	    </div>
	    <div class="write_view"><a class="write_href" href="write_view">글작성</a></div>
	    <div style="clear:both"></div>
    </div>
    <table class="board_table">
        <thead class="board_list_head">
            <tr>
                <th scope="col" class="board_no"><span>번호</span></th>
                <th scope="col" class="board_title"><span>제목</span></th>
                <th scope="col" class="board_name"><span>글쓴이</span></th>
                <th scope="col" class="board_date"><span>날짜</span></th>
                <th scope="col" class="board_hit"><span>조회</span></th>
            </tr>
        </thead>
        <tbody>
            <tr class="board_list_notice">

            </tr>
            <c:forEach items="${list}" var="dto">
                <tr class="board_content">
                    <td class="content_num">${dto.bId}</td>
                    <td><a class="content_title" href="content_view?bId=${dto.bId}">${dto.bTitle}
                    	<c:if test="${dto.BOARD_COMMENT>0}">
                    	<span style="color:red;">(${dto.BOARD_COMMENT})</span>
                    	</c:if>
                    	</a>
                    </td>
                    <td class="content_name">${dto.bName}</td>
                    <td class="content_Date">${dto.bDate}</td>
                    <td class="content_Hit">${dto.bHit}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

	<div class="pagetable board_box">
		<!-- 왼쪽 화살표 -->
		<c:if test="${page.prev}">
			<a style="text-decoration: none;" href="javascript:page(${page.getStartPage()-1});">이전</a>
		</c:if>

		<!-- 페이지 숫자 표시 -->
		<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
			<a style="text-decoration: none;" href="${pageContext.request.contextPath}/board/list?pagenum=${idx}&contentnum=10&text_find=${text_find}">${idx}</a>
		</c:forEach>

		<!-- 오른쪽 화살표 -->
		<c:if test="${page.next}">
			<a style="text-decoration: none;" href="javascript:page(${page.getEndPage()+1});">다음</a>
		</c:if>
	</div>
</body>
</html>