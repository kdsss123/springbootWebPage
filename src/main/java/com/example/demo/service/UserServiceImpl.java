package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper uMapper;
	
	@Override
	public boolean login(String userId, String userPw) {

		UserDto user = uMapper.selectOneUser(userId);
		
		if(user != null) {
			if(user.getUserPw().equals(userPw));
				return true;
		}
		
		return false;
	}

	@Override
	public boolean join(UserDto user) { // 회원가입
		if(uMapper.selectOneUser(user.getUserId()) == null) { // 아이디 중복여부 체크
			uMapper.insertUser(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void modify(UserDto user) { 

		uMapper.updateUser(user);
	}

	@Override
	public void withdraw(String userId) {
		uMapper.deleteUser(userId); // 탈퇴
		
	}

	@Override
	public UserDto getUser(String userId) {
		return uMapper.selectOneUser(userId);
	}

	@Override
	public List<UserDto> getUserList() {
		return uMapper.selectAllUser();
	}

}
