<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/header.jspf" %>
<h2 class="page-title">${settings.groupName} · ${fn:substring(ym,0,4)}년 ${fn:substring(ym,5,7)}월 회비 요약</h2>
<div class="content">
<%@ include file="common/summary.jspf" %>
<div class="panel">
  <h5>미납자</h5>
  <c:choose><c:when test="${empty unpaid}"><p class="text-success">이번 달은 모두 냈어요 🎉</p></c:when>
  <c:otherwise><p><c:forEach var="p" items="${unpaid}" varStatus="st"><span class="badge-unpaid">${p.memberName}</span> </c:forEach></p></c:otherwise></c:choose>
  <p class="text-muted small">납부일: 매월 ${settings.dueDay}일 · 계좌: <c:out value="${settings.accountInfo}"/></p>
  <a href="/dues?ym=${ym}" class="btn btn-primary">이번 달 현황 보기</a>
</div></div>
<%@ include file="common/footer.jspf" %>
