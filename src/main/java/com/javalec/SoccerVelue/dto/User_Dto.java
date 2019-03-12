package com.javalec.SoccerVelue.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Entity;

@Entity
public class User_Dto {
	@Id
	@Column
    @Pattern(regexp="\\S{4,12}", message="아이디는 공백을 제외한  4~14자로 입력해주세요.")    
	private String UserId;
	
    private String Email;
	@Column
    @NotNull
    // 정규화체크(공백이없는 4~12자리 문자)
    @Pattern(regexp="\\S{4,12}", message="비밀번호는 공백을 제외한  4~14자로 입력해주세요.")    
    private String Password;
    @Column
    @NotNull
    //@NotEmpty(message="비밀번호를 입력해주세요.")
    @Pattern(regexp="\\S{1,12}", message="비밀번호를 잘못 입력하셨습니다.")    
    private String checkPw;
    
    public String getUserId() {
		return UserId;
	}

	public void setUserId(String userid) {
		this.UserId = userid;
	}
    
    public String getCheckPw() {
		return checkPw;
	}

	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}

	//비밀번호 확인
    public boolean isPwEqualToCheckPw() {
        return Password.equals(checkPw);
    }
 
    public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
        
}
