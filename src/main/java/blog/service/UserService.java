package blog.service;

import blog.domain.user.User;
import blog.domain.user.dto.JoinReqDto;
import blog.domain.user.dto.LoginReqDto;
import blog.domain.user.dto.UpdateReqDto;

public class UserService {

	
	public int 회원가입(JoinReqDto dto) {
		
		return -1;
		
	}
	
	public User 로그인(LoginReqDto dto) {
		
		return null;
		
	}
	
	public int 회원수정(UpdateReqDto dto) {
		return -1;
	}
	
	public int 아이디중복체크(String username) {
		
		return -1;
	}
}
