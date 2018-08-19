package cn.web.action;

import java.util.List;

import org.apache.logging.log4j.core.config.json.JsonConfiguration;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.web.entity.BaseDict;
import cn.web.service.BaseDictService;
import net.sf.json.JSONArray;

public class BaseDictAction extends ActionSupport{

	private BaseDictService baseDictService;
	
	private String dict_type_code;
	
	@Override
	public String execute() throws Exception {
		//1 调用Service根据typecode获得数据字典对象list
		List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
		//System.out.println(list);
		//2 将list转换为 json格式
		String json = JSONArray.fromObject(list).toString();
		//3 将json发送给浏览器
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		System.out.println("json发送给浏览器");
		System.out.println(json);
		
		return null;//告诉struts2不需要进行结果处理
	}

	public String getDict_type_code() {
		return dict_type_code;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	
}
