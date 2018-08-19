package cn.web.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import cn.web.dao.CustomerDao;
import cn.web.entity.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	@SuppressWarnings("all")
	public List<Object[]> getIndustryCount() {
		// TODO Auto-generated method stub
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {

			String sql = "select bd.dict_item_name, count(*) total from data.cst_customer c,data.base_dict bd\r\n" + 
					" where c.cust_industry = bd.dict_id\r\n" + 
					" group by c.cust_industry";
			
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

	
	
}
