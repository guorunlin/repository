package cn.web.dao;


import java.util.List;

import cn.web.entity.Customer;

public interface CustomerDao extends BaseDao<Customer> {
	
	//按照行业统计客户数量
	List<Object[]> getIndustryCount();
}
