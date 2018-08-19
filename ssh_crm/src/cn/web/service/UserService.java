package cn.web.service;

import cn.web.entity.User;

public interface UserService {
	
	//登陆方法
	User getUserByCodePassword(User u);
	
	//注册用户
	void saveUser(User u);
	//查出用户并回显
	User getUserById(Long user_id);
	//修改密码
	void update(User user);
	
}
