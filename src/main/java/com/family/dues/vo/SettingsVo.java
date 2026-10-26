package com.family.dues.vo;
public class SettingsVo { private String groupName, accountInfo, kakaoTemplate; private long monthlyAmount; private int dueDay=10;
 public String getGroupName(){return groupName;} public void setGroupName(String v){groupName=v;} public String getAccountInfo(){return accountInfo;} public void setAccountInfo(String v){accountInfo=v;}
 public String getKakaoTemplate(){return kakaoTemplate;} public void setKakaoTemplate(String v){kakaoTemplate=v;} public long getMonthlyAmount(){return monthlyAmount;} public void setMonthlyAmount(long v){monthlyAmount=v;}
 public int getDueDay(){return dueDay;} public void setDueDay(int v){dueDay=v;} }
