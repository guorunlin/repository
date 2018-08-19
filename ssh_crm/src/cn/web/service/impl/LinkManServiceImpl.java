package cn.web.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.web.dao.LinkManDao;
import cn.web.entity.Customer;
import cn.web.entity.LinkMan;
import cn.web.service.LinkManService;
import cn.web.utils.PageBean;

@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class LinkManServiceImpl implements LinkManService {
	
	private LinkManDao lmd;
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(LinkMan linkMan) {
		// TODO Auto-generated method stub
		lmd.saveOrUpdate(linkMan);
	}

	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		
		//1 调用Dao查询总记录数
		Integer totalCount = lmd.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 调用Dao查询分页列表数据
		List<LinkMan> list = lmd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//4 列表数据放入pageBean中.并返回
		pb.setList(list);
		
		return pb;
	}

	@Override
	public LinkMan getById(Long lkm_id) {
		// TODO Auto-generated method stub
		LinkMan linkMan = lmd.getById(lkm_id);
		return linkMan;
	}

}
