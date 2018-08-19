package cn.web.action;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.web.entity.User;
import cn.web.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private User user = new User();
	
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String logout() throws Exception {
		
		Map session = ActionContext.getContext().getSession();
		
		if(session != null)
			session.remove("user");
		
		// 重定向到项目首页
		return "toHome";
	}

	
	public String login() throws Exception {
		//1 调用Service执行登陆逻辑
		User u = userService.getUserByCodePassword(user);
		//2 将返回的User对象放入session域
		ActionContext.getContext().getSession().put("user", u);
		//3 重定向到项目首页
		return "toHome";
	}
	
	public String register() throws Exception {
		//1 调用Service保存注册用户
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("error", e.getMessage());
			return "regist";
		}
		return "toLogin";
	}

	
	//密码修改页面回显
	public String revise() throws Exception {
		
		User u = userService.getUserById(user.getUser_id());
		
		ActionContext.getContext().put("user", u);
		
		return "revise";
	}
	
	public String toEdit() throws Exception {
		/*User u = userService.getUserById(user.getUser_id());*/
		userService.update(user);
		
		ActionContext.getContext().getSession().remove("user");
		
		return "toLogin";
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	
}
