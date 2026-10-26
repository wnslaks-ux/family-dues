<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/header.jspf" %>
<h2 class="page-title">${fn:substring(ym,0,4)}년 ${fn:substring(ym,5,7)}월 회비 현황</h2>
<div class="content">
<form method="get" action="/dues" class="d-flex gap-2 align-items-center mb-3">
  <a class="btn btn-outline-secondary btn-sm" href="/dues?ym=${ymPrev}">&laquo; 이전달</a>
  <input type="month" name="ym" value="${ym}" class="form-control form-control-sm" style="max-width:170px" onchange="this.form.submit()">
  <a class="btn btn-outline-secondary btn-sm" href="/dues?ym=${ymNext}">다음달 &raquo;</a>
</form>
<%@ include file="common/summary.jspf" %>
<c:if test="${not empty unpaid}">
  <div class="mb-2"><button type="button" class="btn btn-kakao" data-msg="<c:out value='${allUnpaidMsg}'/>" onclick="copyText(this.dataset.msg,this)">미납 전원 문구 복사 (${fn:length(unpaid)}명)</button>
  <span class="text-muted small ms-2">카카오톡 자동 발송은 불가 — 문구 복사 후 카톡에 붙여넣기 하세요.</span></div>
</c:if>
<div class="table-responsive"><table class="table table-sm table-bid align-middle">
<thead><tr><th>이름</th><th>연락처</th><th>납부상태</th><th>납부일</th><th class="text-end">금액(원)</th><th>액션</th></tr></thead>
<tbody>
<c:forEach var="p" items="${payments}">
<tr><td class="fw-semibold">${p.memberName}</td><td><c:out value="${p.phone}"/></td>
  <td><c:choose><c:when test="${p.paid}"><span class="badge-paid">완납</span></c:when><c:otherwise><span class="badge-unpaid">미납</span></c:otherwise></c:choose></td>
  <td>${p.paidAt}</td><td class="text-end"><fmt:formatNumber value="${p.amount}"/></td>
  <td class="text-nowrap">
    <sec:authorize access="hasRole('ADMIN')">
    <form method="post" action="/dues/${p.id}/paid" class="d-inline"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><input type="hidden" name="ym" value="${ym}">
      <c:choose><c:when test="${p.paid}"><input type="hidden" name="paid" value="false"><button class="btn btn-sm btn-outline-danger">미납으로</button></c:when>
      <c:otherwise><input type="hidden" name="paid" value="true"><button class="btn btn-sm btn-success">완납 처리</button></c:otherwise></c:choose></form>
    </sec:authorize>
    <c:if test="${!p.paid}"><button type="button" class="btn btn-sm btn-kakao" data-msg="<c:out value='${p.message}'/>" onclick="copyText(this.dataset.msg,this)">카톡문구 복사</button></c:if>
  </td></tr>
</c:forEach>
<c:if test="${empty payments}"><tr><td colspan="6" class="text-center text-muted">활성 멤버가 없습니다.</td></tr></c:if>
</tbody></table></div>
<sec:authorize access="!hasRole('ADMIN')"><p class="text-muted small">완납 체크는 관리자만 할 수 있어요. <a href="/login">로그인</a></p></sec:authorize>
</div>
<%@ include file="common/footer.jspf" %>
