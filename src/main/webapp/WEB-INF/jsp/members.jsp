<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/header.jspf" %>
<h2 class="page-title">멤버 관리</h2>
<div class="content">
<c:if test="${not empty msg}"><div class="alert alert-success py-2">${msg}</div></c:if>
<div class="table-responsive"><table class="table table-sm table-bid align-middle">
<thead><tr><th>순서</th><th>이름</th><th>연락처</th><th>메모</th><th>상태</th><th></th></tr></thead><tbody>
<c:forEach var="m" items="${members}"><tr><td>${m.sortOrder}</td><td class="fw-semibold"><c:out value="${m.name}"/></td><td><c:out value="${m.phone}"/></td><td><c:out value="${m.memo}"/></td>
<td>${m.active ? '활성' : '<span class="text-muted">비활성</span>'}</td>
<td class="text-nowrap"><a class="btn btn-sm btn-outline-primary" href="/members?edit=${m.id}">수정</a>
<form method="post" action="/members/${m.id}/delete" class="d-inline" onsubmit="return confirm('삭제하면 납부 기록도 함께 지워집니다. 계속할까요? (보관하려면 비활성으로 바꾸세요)')"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><button class="btn btn-sm btn-outline-danger">삭제</button></form></td></tr></c:forEach>
</tbody></table></div>
<form method="post" action="/members" class="panel"><h5>${empty edit.id ? '멤버 추가' : '멤버 수정'}</h5>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><c:if test="${not empty edit.id}"><input type="hidden" name="id" value="${edit.id}"></c:if>
<div class="row g-2">
 <div class="col-md-3"><label class="form-label">이름 *</label><input name="name" class="form-control" required value="<c:out value='${edit.name}'/>"></div>
 <div class="col-md-3"><label class="form-label">연락처(선택)</label><input name="phone" class="form-control" value="<c:out value='${edit.phone}'/>"></div>
 <div class="col-md-3"><label class="form-label">메모</label><input name="memo" class="form-control" value="<c:out value='${edit.memo}'/>"></div>
 <div class="col-md-1"><label class="form-label">순서</label><input type="number" name="sortOrder" class="form-control" value="${edit.sortOrder}"></div>
 <div class="col-md-2"><label class="form-label">상태</label><select name="active" class="form-select"><option value="true" ${edit.active ? 'selected' : ''}>활성</option><option value="false" ${!edit.active ? 'selected' : ''}>비활성</option></select></div>
</div>
<div class="mt-3"><button class="btn btn-primary">저장</button> <c:if test="${not empty edit.id}"><a href="/members" class="btn btn-outline-secondary">취소</a></c:if></div>
<p class="text-muted small mt-2">새 멤버는 아직 생성되지 않은 달 또는 현재 달을 다시 열 때 미납으로 자동 추가됩니다.</p>
</form></div>
<%@ include file="common/footer.jspf" %>
