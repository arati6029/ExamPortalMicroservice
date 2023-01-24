package com.examportal.exam.dto;

public class StudentInfoDTO {
private String name;
private String email;
private String mobile;
private String password;
private String addressLine1;
private String addressLine2;
private String pincode;
private long skillId;
private long experienceId;
public StudentInfoDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public StudentInfoDTO(String name, String email, String mobile, String password, String addressLine1,
		String addressLine2, String pincode, long skillId, long experienceId) {
	super();
	this.name = name;
	this.email = email;
	this.mobile = mobile;
	this.password = password;
	this.addressLine1 = addressLine1;
	this.addressLine2 = addressLine2;
	this.pincode = pincode;
	this.skillId = skillId;
	this.experienceId = experienceId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAddressLine1() {
	return addressLine1;
}
public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
}
public String getAddressLine2() {
	return addressLine2;
}
public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public long getSkillId() {
	return skillId;
}
public void setSkillId(long skillId) {
	this.skillId = skillId;
}
public long getExperienceId() {
	return experienceId;
}
public void setExperienceId(long experienceId) {
	this.experienceId = experienceId;
}


}
