package cn.web.action;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import cn.web.entity.SaleVisit;
import cn.web.entity.User;
import cn.web.service.SaleVisitService;
import cn.web.utils.PageBean;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit = new SaleVisit();
	
	private SaleVisitService svs;
	
	private Integer currentPage;
	private Integer pageSize;
	
	public String list() throws Exception {
		
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
		//判断并封装参数
		if(saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null){
			dc.add(Restrictions.like("customer.cust_id", "%"+saleVisit.getCustomer().getCust_id()+"%"));
		}
		
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = svs.getPageBean(dc, currentPage, pageSize);
		//2 将PageBean放入request域,转发到列表页面显示
		ActionContext.getContext().put("pageBean", pb);
		
		return "list";
	}
	
	//去往编辑页面回显
	public String toEdit() throws Exception {
			//1 调用Service根据id查询客户拜访对象
			SaleVisit sv = svs.getById(saleVisit.getVisit_id());
			//2 将对象放入request域
			ActionContext.getContext().put("saleVisit", sv);
			//3 转发到add.jsp
			return "add";
	}
	
	//添加客户拜访记录
	public String add() throws Exception {
		//0 取出登陆用户,放入SaleVisit实体.表达关系
		User u = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(u);
		//1 调用Service保存客户拜访记录
		svs.save(saleVisit);
		//2 重定向到拜访记录列表Action
		return "toList";
	}



	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
	}



	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public SaleVisit getModel() {
		// TODO Auto-generated method stub
		return saleVisit;
	}
	
}
