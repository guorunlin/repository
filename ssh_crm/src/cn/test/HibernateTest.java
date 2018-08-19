package cn.test;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.web.dao.BaseDictDao;
import cn.web.dao.UserDao;
import cn.web.entity.BaseDict;
import cn.web.entity.Customer;
import cn.web.entity.LinkMan;
import cn.web.entity.User;
import cn.web.service.BaseDictService;
import cn.web.service.CustomerService;
import cn.web.service.LinkManService;
import cn.web.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	
	@Test
	//单独测试hibernate
	public void fun1() {
		Configuration conf = new Configuration().configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		
		User u = new User();
		u.setUser_code("rose");
		u.setUser_name("肉丝");
		u.setUser_password("1234");
		
		session.save(u);
		
		
		
		tx.commit();
		
		session.close();
		
		sf.close();
	}
	
	@Test
	//测试spring管理sessionFactory
	public void fun2() {
	
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		
		User u = new User();
		u.setUser_code("tom");
		u.setUser_name("汤姆");
		u.setUser_password("1234");
		
		session.save(u);
		
		
		
		tx.commit();
		
		session.close();
	}
	
	@Resource(name = "userDao")
	private UserDao ud;
	@Test
	//测试Dao Hibernate模板
	public void fun3() {
		User user = ud.getByUserCode("tom");
		System.out.println(user);
	}
	
	@Resource(name="userService")
	private UserService us;
	@Test
	//测试aop事务
	public void fun4(){
		User u = new User();
		
		u.setUser_code("12");
		u.setUser_name("撒旦");
		u.setUser_password("1234");
		
		
		us.saveUser(u);
	}
	
	@Resource
	private BaseDictDao baseDictDao;
	@Test
	public void fun5() {
		List<BaseDict> list = baseDictDao.getListByTypeCode("001");
		System.out.println(list);
	}
	
	@Resource
	private BaseDictService baseDictService;
	@Test
	public void fun6() {
		List<BaseDict> list = baseDictService.getListByTypeCode("002");
		System.out.println(list);
	}
	
	@Resource
	private CustomerService customerService;
	@Test
	public void fun7() {
		Customer customer = customerService.getById(15l);
		System.out.println(customer);
	}
	
	@Resource
	private LinkManService linkManService;
	@Test
	public void fun8() {
		LinkMan lm = new LinkMan();
		Customer customer = customerService.getById(15l);
		lm.setLkm_name("萨达");
		lm.setLkm_gender('男');
		lm.setLkm_mobile("666");
		lm.setLkm_phone("946");
		lm.setCustomer(customer);
		
		linkManService.save(lm);
	}
}
