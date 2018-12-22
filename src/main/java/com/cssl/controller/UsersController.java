package com.cssl.controller;

import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import com.cssl.vo.Message;
import com.cssl.vo.UsersVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes(types={UsersVo.class})
public class UsersController {

	@Resource(name="usersService")
	private UsersService service;	

	@RequestMapping("/param.action")
	@ResponseBody
	public Message param(UsersVo uvo){
		//对象导航注入
		System.out.println("param.action:"+uvo.getMsg().getMessage());

		Message m = new Message();
		if (uvo.getUsername().equals("admin")
				&& uvo.getPassword().equals("123")) {
			m.setFlag(true);
			m.setMessage("登录成功！");
		} else {
			m.setFlag(false);
			m.setMessage("登录失败！");
		}

		return m;
	}
	

	
	@RequestMapping("/combox.action")
	public void combox(HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String json = "[{\"id\":1,\"text\":\"湖南\"},{\"id\":2,\"text\":\"湖北\"}]";
		
		System.out.println(json);
		out.print(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/combox2.action")
	public void combox2(int id,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(id);
		String json = "";
		
		if(id==1){
			json = "[{\"uid\":1,\"text\":\"长沙\"},{\"uid\":2,\"text\":\"株洲\"}]";
		}else if(id==2){
			json = "[{\"uid\":1,\"text\":\"武汉\"},{\"uid\":2,\"text\":\"襄阳\"}]";
		}
		System.out.println(json);
		out.print(json);
		out.flush();
		out.close();
	}		
		
	/**
	 * @throws IOException
	 */
	@RequestMapping("/dataGrid.action")
	@ResponseBody
	public Map<String, Object> dataGrid(String name,String pass,int page,int rows) throws Exception{
		System.out.println("name:"+name+"\tpass:"+pass);
		System.out.println("page:"+page+"\trows:"+rows);
		//Thread.sleep(2000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", this.service.getUsersCount(null));
		map.put("rows", this.service.findAllUsers(page, rows));

		return map;
	}	
	
	@RequestMapping("/combogrid.action")
	@ResponseBody
	public Map<String, Object> combogrid(String q,HttpServletResponse response) throws IOException{
		System.out.println("username:"+q);
		Users u = new Users();
		u.setUsername(q);
		
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("total", this.service.getUsersCount(null));
		map.put("rows", this.service.findAllUsers(u,1,8,"id","asc"));

		return map;
	}

	/**
	 * edatagrid.html
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/edatagrid.action")
	public void edatagrid(HttpServletResponse response) throws IOException {

		String json = "[\n" +
				"\t{\"productid\":\"FI-SW-01\",\"unitcost\":10.00,\"status\":\"P\",\"listprice\":16.50,\"attr1\":\"Large\",\"id\":\"EST-1\"},\n" +
				"\t{\"productid\":\"K9-DL-01\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Spotted Adult Female\",\"id\":\"EST-2\"},\n" +
				"\t{\"productid\":\"RP-SN-01\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Venomless\",\"id\":\"EST-3\"},\n" +
				"\t{\"productid\":\"RP-LI-02\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Green Adult\",\"id\":\"EST-5\"},\n" +
				"\t{\"productid\":\"FL-DSH-01\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":58.50,\"attr1\":\"Tailless\",\"id\":\"EST-6\"},\n" +
				"\t{\"productid\":\"FL-DSH-01\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":23.50,\"attr1\":\"With tail\",\"id\":\"EST-7\"},\n" +
				"\t{\"productid\":\"FL-DLH-02\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":93.50,\"attr1\":\"Adult Female\",\"id\":\"EST-8\"},\n" +
				"\t{\"productid\":\"FL-DLH-02\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":93.50,\"attr1\":\"Adult Male\",\"id\":\"EST-9\"},\n" +
				"\t{\"productid\":\"RP-SN-01\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Rattleless\",\"id\":\"EST-4\"},\n" +
				"\t{\"productid\":\"AV-CB-01\",\"unitcost\":92.00,\"status\":\"P\",\"listprice\":193.50,\"attr1\":\"Adult Male\",\"id\":\"EST-10\"}\n" +
				"]";

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	
	@InitBinder
	public void initBind(ServletRequestDataBinder binder){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,false));
	}

}
