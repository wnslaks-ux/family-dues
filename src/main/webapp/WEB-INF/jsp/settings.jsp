<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/header.jspf" %>
<h2 class="page-title">회비 설정</h2>
<div class="content">
<c:if test="${not empty msg}"><div class="alert alert-success py-2">${msg}</div></c:if>
<form method="post" action="/settings" class="panel"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<div class="row g-2">
 <div class="col-md-4"><label class="form-label">모임 이름</label><input name="groupName" class="form-control" value="<c:out value='${settings.groupName}'/>"></div>
 <div class="col-md-4"><label class="form-label">월 회비(원)</label><input type="number" min="0" name="monthlyAmount" class="form-control" value="${settings.monthlyAmount}"><div class="form-text">아직 미정이면 0으로 두고 나중에 입력하세요.</div></div>
 <div class="col-md-4"><label class="form-label">납부일 (매월 1~28일)</label><input type="number" min="1" max="28" name="dueDay" class="form-control" value="${settings.dueDay}"></div>
 <div class="col-12"><label class="form-label">계좌 안내 문구</label><input name="accountInfo" class="form-control" placeholder="예: 카카오뱅크 모임통장 3333-00-0000000 (예금주)" value="<c:out value='${settings.accountInfo}'/>"></div>
 <div class="col-12"><label class="form-label">카카오톡 미납 안내 문구 템플릿</label><textarea name="kakaoTemplate" rows="6" class="form-control"><c:out value="${settings.kakaoTemplate}"/></textarea>
 <div class="form-text">사용 가능: {이름} {금액} {계좌} {모임} {월}</div></div>
</div>
<div class="mt-3"><button class="btn btn-primary px-5">저장</button></div>
</form></div>
<%@ include file="common/footer.jspf" %>
