package com.cssl.dao;

import com.cssl.pojo.Users;

import java.util.List;
import java.util.Map;

public interface UsersDao {

	public int save(Users user);
	
	public int updateUsers(Users user);
	
	public int deleteUsers(List<Integer> ids);
	
	public Users findUsers(Users user);
	
	public long findUsersCount(Users user);
	
	public List<Users> findAllUsers(Map<String,Object> param);
	
	public List<Map<String,Object>> findByCondition(Map<String,Object> param);
}
