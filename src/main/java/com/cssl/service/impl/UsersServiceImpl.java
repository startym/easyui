package com.cssl.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cssl.dao.HouseDao;
import com.cssl.dao.UsersDao;
import com.cssl.pojo.House;
import com.cssl.pojo.Users;
import com.cssl.pojo.UsersHouse;
import com.cssl.service.UsersService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao udao;
	@Autowired
	private HouseDao hdao;


	@Override	
	public int saveUser(Users user) throws IOException {
		
		if(user==null){
			throw new RuntimeException();
		}	
		return this.udao.save(user);		
	
	}


	@Override	
	public Users getUsersById(int id) {
		System.out.println("UsersServiceImpl getUsersById...");
		return null;
	}


	@Override
	public boolean getUsers(Users user) {
		Users u = this.udao.findUsers(user);
		if(u==null)
			return false;
		return true;
	}

	@Override
	public long getUsersCount(Users user) {
		return this.udao.findUsersCount(user);
	}

	@Override
	public int updateUser(Users user) {
		return this.udao.updateUsers(user);		
	}

	@Override
	public int deleteUser(List<Integer> ids) {
		return this.udao.deleteUsers(ids);
	}

	@Override
	public List<Users> findAllUsers(int page, int rows) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", (page-1)*rows);
		param.put("rows", rows);
		
		return this.udao.findAllUsers(param);
	}

	@Override
	public List<Map<String,Object>> findAllUsers(Users user,int page, int rows,
			String sort,String order) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", (page-1)*rows);
		param.put("rows", rows);
		param.put("sort", sort);
		param.put("order", order);
		param.put("id", user.getId());
		param.put("username", user.getUsername());
		
		return this.udao.findByCondition(param);
	}


	@Override
	public List<House> findAllHouse() {
		return hdao.findAllHouse();
	}

}
