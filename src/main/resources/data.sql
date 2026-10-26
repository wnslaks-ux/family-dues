-- 최초 1회만 시드 (테이블이 비어 있을 때). 이후 수정은 관리자 화면에서.
INSERT INTO member(name, phone, memo, active, sort_order)
SELECT * FROM (VALUES ('김지애','','',TRUE,1),('김지은','','',TRUE,2),('김지영','','',TRUE,3),('김지원','','',TRUE,4)) v
WHERE NOT EXISTS (SELECT 1 FROM member);
INSERT INTO dues_settings(id, group_name, monthly_amount, due_day, account_info, kakao_template)
SELECT 1, '우리 가족 모임', 0, 10, '계좌는 설정에서 입력하세요',
 '[{모임}] {월} 회비 안내' || CHAR(10) || '{이름}님, {월} 회비 {금액}원이 아직 입금되지 않았어요.' || CHAR(10) || '입금 계좌: {계좌}' || CHAR(10) || '확인 부탁드려요 :)'
WHERE NOT EXISTS (SELECT 1 FROM dues_settings WHERE id=1);
