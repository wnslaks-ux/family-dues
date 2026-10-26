package com.family.dues.service;
import com.family.dues.mapper.DuesMapper;import com.family.dues.vo.*;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import java.text.NumberFormat;import java.time.YearMonth;import java.util.*;
@Service public class DuesService {
 private final DuesMapper m; public DuesService(DuesMapper m){this.m=m;}
 public static String normalize(String ym){ try{ return YearMonth.parse(ym).toString(); }catch(Exception e){ return YearMonth.now().toString(); } }
 public SettingsVo settings(){ return m.settings(); }
 @Transactional public List<PaymentVo> month(String ym){
   SettingsVo s=m.settings(); m.createMissing(ym, s.getMonthlyAmount()); List<PaymentVo> list=m.payments(ym);
   for(PaymentVo p:list) p.setMessage(render(s,p,ym)); return list; }
 public String render(SettingsVo s, PaymentVo p, String ym){
   String t=s.getKakaoTemplate()==null?"":s.getKakaoTemplate(); YearMonth y=YearMonth.parse(ym);
   return t.replace("{이름}",p.getMemberName()).replace("{금액}",NumberFormat.getInstance(Locale.KOREA).format(p.getAmount()))
     .replace("{계좌}",nz(s.getAccountInfo())).replace("{모임}",nz(s.getGroupName())).replace("{월}",y.getYear()+"년 "+y.getMonthValue()+"월"); }
 private static String nz(String v){return v==null?"":v;}
 public void setPaid(long id, boolean paid){ m.setPaid(id,paid); }
 public List<MemberVo> members(){ return m.members(); } public MemberVo member(long id){ return m.member(id); }
 public void save(MemberVo v){ if(v.getId()==null) m.insertMember(v); else m.updateMember(v); }
 @Transactional public void delete(long id){ m.deletePaymentsOf(id); m.deleteMember(id); }
 public void saveSettings(SettingsVo s){ if(s.getDueDay()<1)s.setDueDay(1); if(s.getDueDay()>28)s.setDueDay(28); m.updateSettings(s); }
}
