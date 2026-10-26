package com.family.dues.mapper;
import com.family.dues.vo.*;import org.apache.ibatis.annotations.*;import java.util.List;
@Mapper public interface DuesMapper {
 @Select("SELECT * FROM member ORDER BY sort_order, id") List<MemberVo> members();
 @Select("SELECT * FROM member WHERE id=#{id}") MemberVo member(long id);
 @Insert("INSERT INTO member(name,phone,memo,active,sort_order) VALUES(#{name},#{phone},#{memo},#{active},#{sortOrder})") void insertMember(MemberVo m);
 @Update("UPDATE member SET name=#{name},phone=#{phone},memo=#{memo},active=#{active},sort_order=#{sortOrder} WHERE id=#{id}") void updateMember(MemberVo m);
 @Delete("DELETE FROM payment WHERE member_id=#{id}") void deletePaymentsOf(long id);
 @Delete("DELETE FROM member WHERE id=#{id}") void deleteMember(long id);
 @Select("SELECT * FROM dues_settings WHERE id=1") SettingsVo settings();
 @Update("UPDATE dues_settings SET group_name=#{groupName},monthly_amount=#{monthlyAmount},due_day=#{dueDay},account_info=#{accountInfo},kakao_template=#{kakaoTemplate} WHERE id=1") void updateSettings(SettingsVo s);
 @Insert("INSERT INTO payment(member_id,year_month,amount,paid) SELECT m.id,#{ym},#{amount},FALSE FROM member m WHERE m.active=TRUE AND NOT EXISTS(SELECT 1 FROM payment p WHERE p.member_id=m.id AND p.year_month=#{ym})")
 int createMissing(@Param("ym") String ym, @Param("amount") long amount);
 @Select("SELECT p.*, m.name member_name, m.phone FROM payment p JOIN member m ON m.id=p.member_id WHERE p.year_month=#{ym} ORDER BY m.sort_order, m.id")
 List<PaymentVo> payments(String ym);
 @Update("UPDATE payment SET paid=#{paid}, paid_at=CASE WHEN #{paid} THEN CURRENT_DATE ELSE NULL END WHERE id=#{id}") void setPaid(@Param("id") long id, @Param("paid") boolean paid);
}
