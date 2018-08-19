package cn.web.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.web.entity.SaleVisit;
import cn.web.utils.PageBean;

public interface SaleVisitService {
	
	//保存客户拜访记录
	void save(SaleVisit saleVisit);
	//分页业务方法
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//根据id获得客户对象
	SaleVisit getById(String visit_id);

	
}
