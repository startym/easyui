package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Users implements Serializable {

	private Integer id;
	private String username;
	private String password;
	private Date bornDate;
	private String path;
	private House house;


	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Users() {
		super();
	}

	public Users(int id, String username, String password, Date bornDate
			) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.bornDate = bornDate;
		
	}

	
	
	
	
}
