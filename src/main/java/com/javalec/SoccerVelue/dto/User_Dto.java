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
    @Pattern(regexp="\\S{4,12}", message="���̵�� ������ ������  4~14�ڷ� �Է����ּ���.")    
	private String UserId;
	
    private String Email;
	@Column
    @NotNull
    // ����ȭüũ(�����̾��� 4~12�ڸ� ����)
    @Pattern(regexp="\\S{4,12}", message="��й�ȣ�� ������ ������  4~14�ڷ� �Է����ּ���.")    
    private String Password;
    @Column
    @NotNull
    //@NotEmpty(message="��й�ȣ�� �Է����ּ���.")
    @Pattern(regexp="\\S{1,12}", message="��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.")    
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

	//��й�ȣ Ȯ��
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
