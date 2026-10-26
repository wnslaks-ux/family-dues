package com.family.dues.vo;
import java.time.LocalDate;
public class PaymentVo { private Long id, memberId; private String memberName, phone, yearMonth, note, message; private long amount; private boolean paid; private LocalDate paidAt;
 public Long getId(){return id;} public void setId(Long v){id=v;} public Long getMemberId(){return memberId;} public void setMemberId(Long v){memberId=v;}
 public String getMemberName(){return memberName;} public void setMemberName(String v){memberName=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getYearMonth(){return yearMonth;} public void setYearMonth(String v){yearMonth=v;} public String getNote(){return note;} public void setNote(String v){note=v;}
 public String getMessage(){return message;} public void setMessage(String v){message=v;} public long getAmount(){return amount;} public void setAmount(long v){amount=v;}
 public boolean isPaid(){return paid;} public void setPaid(boolean v){paid=v;} public LocalDate getPaidAt(){return paidAt;} public void setPaidAt(LocalDate v){paidAt=v;} }
