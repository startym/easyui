package com.cssl.service;

import com.cssl.pojo.House;
import com.cssl.pojo.Users;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UsersService {

	public int saveUser (Users user) throws IOException;
	
	public int updateUser (Users user);
	
	public int deleteUser (List<Integer> ids);
	
	public Users getUsersById(int id);
	
	public List<Users> findAllUsers(int page,int rows);
	
	public List<Map<String,Object>> findAllUsers(Users user, int page, int rows, String sort, String order);
	
	public boolean getUsers(Users user);
	
	public long getUsersCount(Users user);
	
	public List<House> findAllHouse();
}
