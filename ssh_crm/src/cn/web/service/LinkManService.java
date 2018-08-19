package cn.web.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.web.entity.LinkMan;
import cn.web.utils.PageBean;

public interface LinkManService {
	//保存联系人
	void save(LinkMan linkMan);
	//分页业务方法
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//根据id获得LinkMan对象
	LinkMan getById(Long lkm_id);

}
