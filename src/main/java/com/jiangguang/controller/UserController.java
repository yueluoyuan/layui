package com.jiangguang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangguang.entity.ResultMsg;
import com.jiangguang.entity.User;
import com.jiangguang.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/userList")
	public ResultMsg<User> userList(User user, @RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
		
		ResultMsg<User> resultMsg=new ResultMsg<User>();
		List<User> userList = userService.getUserList(user,page,limit);
		long count = userService.count(user);
		resultMsg.setData(userList);
		resultMsg.setCount(count);
		resultMsg.setCode(0);
		
		return resultMsg;
		
	}
	
	@RequestMapping("/add")
	public String addUser() {
		
		return "user/add";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public ResultMsg<User> saveUser(User user) {
		
		ResultMsg<User> resultMsg=new ResultMsg<User>();
		userService.saveUser(user);
		resultMsg.setMsg("添加成功");
		
		return resultMsg;
	}
	

	@RequestMapping("/toEdit")
	public String preEditUser(Integer id,Model model) {		
		
		User user=userService.findUserById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Map<String,Object> editUser(User user) {
		
		Map<String,Object> result=new HashMap<String, Object>();
		userService.saveUser(user);
		result.put("msg", "修改成功");
		return result;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String,Object> deleteUser(Integer id) {

		Map<String,Object> result=new HashMap<String, Object>();
		userService.deleteUserById(id);
		result.put("msg", "删除成功！");
		result.put("success", true);
		return result;
	}
}
