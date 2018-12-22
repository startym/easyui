package com.cssl.controller;

import com.cssl.pojo.House;
import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import com.cssl.vo.Message;
import com.cssl.vo.Tree;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class DataController {
	
	@Resource(name="usersService")
	private UsersService service;

	@RequestMapping("/house.action")
	public List<House> combobox(){
		System.out.println("combobox...");
		return service.findAllHouse();
	}

	@RequestMapping(value="/tree2.action",produces="application/json;charset=utf-8")
	public List<Tree> tree2(){
		
		Tree root = new Tree(1,"我的文档");
		root.setState("open");
		
		Tree t11 = new Tree(11,"照片");
		Tree t12 = new Tree(12,"程序");
		Tree t13 = new Tree(13,"index.html");
		Tree t14 = new Tree(14,"about.html");		
		
		List<Tree> list = new ArrayList<Tree>();
		list.add(t11);
		list.add(t12);
		list.add(t13);
		list.add(t14);
		root.setChildren(list);			
		
		List<Tree> tree = new ArrayList<Tree>();
		tree.add(root);
		
		return tree;		
	}
	
	@RequestMapping("/tree.action")
	public List<Tree> tree(){		
		Tree root = new Tree(1,"我的文档");
		root.setState("open");
		
		Tree t11 = new Tree(11,"照片");
		Tree t12 = new Tree(12,"程序");
		Tree t13 = new Tree(13,"index.html");
		Tree t14 = new Tree(14,"about.html");
		t11.setState("closed");
		
		List<Tree> list = new ArrayList<Tree>();
		list.add(t11);
		list.add(t12);
		list.add(t13);
		list.add(t14);
		root.setChildren(list);	
		
		Tree t21 = new Tree(21,"朋友");
		Tree t22 = new Tree(22,"老婆");
		List<Tree> list2 = new ArrayList<Tree>();
		list2.add(t21);
		list2.add(t22);		
		t11.setChildren(list2);			
		
		List<Tree> tree = new ArrayList<Tree>();
		tree.add(root);
		return tree;		
	}
	
	//page=1&rows=10&sort=id&order=asc
	@RequestMapping("/dataGrid2.action")
	public Map<String, Object> dataGrid2(Users user,
			int page,int rows,String order,String sort){
		System.out.println("*******dataGrid2:"+rows+"\t"+page);
		System.out.println("*******dataGrid2:"+order+"\t"+sort);
		System.out.println(user.getId()+":"+user.getUsername());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", this.service.getUsersCount(user));		
		map.put("rows", this.service.findAllUsers(user, page, rows, sort, order));

		return map;
	}		
	
	@RequestMapping("/dataDel.action")
	public Message dataDelUser(String id) throws IOException {
			
		System.out.println(id);
		String[] str = id.split(",");
		Integer[] is = new Integer[str.length];
		for(int i=0;i<str.length;i++){			
			is[i]=Integer.parseInt(str[i]);
		}			
		List<Integer> list = Arrays.asList(is);

		Message m = new Message();	
		if(this.service.deleteUser(list)>0){
			m.setFlag(true);
			m.setMessage("删除成功！");
		}else{
			m.setFlag(false);
			m.setMessage("删除失败！");
		}			

		return m;
	}

	/**
	 * 新增和修改
	 */
	@RequestMapping("/edit.action")
	public Message editUser(Users user)
			throws IOException {
		System.out.println("edit:"+user.getHouse().getHid());
				
		Message m = new Message();
		
		if(user.getId()==0){
			if (this.service.saveUser(user)>0) {
				m.setFlag(true);
				m.setMessage("添加成功！");
			} else {
				m.setFlag(false);
				m.setMessage("添加失败！");
			}
		}else{
			if (this.service.updateUser(user)>0) {
				m.setFlag(true);
				m.setMessage("修改成功！");
			} else {
				m.setFlag(false);
				m.setMessage("修改失败！");
			}
		}

		return m;
	}
	

	
	@RequestMapping("/addRow.action")
	public Map<String, Object> addRow(String id,String productid)
			throws IOException {
		System.out.println("addRow:"+id+"\tproductid:"+productid);

		//调用业务层完成添加

		Map<String, Object> e = new HashMap<String, Object>();
		e.put("isError", false);	//true表示失败
		e.put("msg","提示语句！！");
	
		return e;
	}
	
	@RequestMapping("/updateRow.action")
	public Map<String, Object> updateRow(String id,String productid)
			throws IOException {
		System.out.println("updateRow:"+id+"\tproductid:"+productid);

		//调用业务层完成修改

		Map<String, Object> e = new HashMap<String, Object>();
		e.put("isError", false);	//true表示失败
		e.put("msg","修改失败！！");
	
		return e;
	}
	
	@RequestMapping("/destroyRow.action")
	public Map<String, Object> destroyRow(String id,String productid)
			throws IOException {
		System.out.println("destroyRow:"+id+"\tproductid:"+productid);

		//调用业务层完成删除

		Map<String, Object> e = new HashMap<String, Object>();
		e.put("isError", true);	//true表示失败
		e.put("msg","删除失败！！");
	
		return e;
	}
		
	@InitBinder
	public void initBind(ServletRequestDataBinder binder){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,false));
	}

}
