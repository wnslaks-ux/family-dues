package com.family.dues.vo;
public class MemberVo { private Long id; private String name, phone, memo; private boolean active=true; private int sortOrder;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;} public String getMemo(){return memo;} public void setMemo(String v){memo=v;}
 public boolean isActive(){return active;} public void setActive(boolean v){active=v;} public int getSortOrder(){return sortOrder;} public void setSortOrder(int v){sortOrder=v;} }
