package com.qin.domain;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class Test2 {
	private static Logger log = Logger.getLogger(Test2.class);// 获取日志记录器
	@Test
	public void test3() {
		

		Configuration config = new Configuration().configure(); // 创建配置对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry(); // 创建服务注册对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry); // 创建会话工厂对象
		Session session = sessionFactory.getCurrentSession(); // 创建会话对象
		// SchemaExport export = new SchemaExport(config);
		// export.create(true, true);// true 在控制台打印sql语句，true 导入sql语句到数据库，即可执行
		Transaction tx = session.beginTransaction();

		// Query query = session.createQuery("from Customer");
		// List<Customer> customers =query.list();
		// for (Customer customer : customers) {
		// List<OrderForm> orderFormss=customer.getOrderForms();
		// for (OrderForm orderForm : orderFormss) {
		// System.out.println(orderForm.getStatus());;
		// }
		// }

		// Query query2 = session.createQuery("from OrderItem");
		// List<OrderItem> orderForms =query2.list();
		// for (OrderItem orderForm : orderForms) {
		// System.out.println(orderForm.toString());
		//
		// }
		//
		
		/* String hql=" from Commodity where category = ? ";
		 Query query3 = session.createQuery(hql);
		 query3.setString(0,"电子书");
		 List<List> customers2 = query3.list();
		 for (List list : customers2) {
		 System.out.println(list.get(0));
		 System.out.print(list.get(1));
		 System.out.print(list.get(2));
		 }*/
		/*
		 * Query query4 = session.
		 * createQuery(" from Commodity c where c.price >= 400 and c.description = null"
		 * ); List<Commodity> commodities = query4.list(); for (Commodity commodity :
		 * commodities) { System.out.println(commodity.toString()); }
		 */
		// 查询一个对象
		/*Query query5 = session.createQuery("from Commodity c where c.id = 1");
		Commodity commodity = (Commodity) query5.uniqueResult();
		System.out.println(commodity);*/

		/*
		 * List<Object[]> results =
		 * session.createCriteria(Commodity.class).add(Restrictions.isNotNull(
		 * "description")) .add(Restrictions.or(Restrictions.ge("price", 1000.0),
		 * Restrictions.eq("price", 400.0))) .addOrder(Order.desc("price"))
		 * .setProjection( Projections.projectionList()
		 * .add(Projections.groupProperty("seller")) .add(Projections.rowCount())
		 * .add(Projections.avg("price")) ) .setMaxResults(10) .list(); for (Object[]
		 * object : results) { System.out.print(((Seller) object[0]).getName() + " ");
		 * System.out.print(object[1]+" "); System.out.print(object[2]+" ");
		 * System.out.println(); }
		 */
		/*
		 * DetachedCriteria 提供了 2 个静态方法 forClass(Class) 或 forEntityName(Name)
		 * 进行DetachedCriteria 实例的创建。 Spring 的框架提供了getHibernateTemplate ().
		 * findByCriteria(detachedCriteria) 方法可以很方便地根据DetachedCriteria 来返回查询结 果。
		 */
		
		List<Customer> results1 = DetachedCriteria.forClass(Customer.class).getExecutableCriteria(session).list();
		for (Customer object : results1) {
			System.out.println(object);

		}
		
		

		tx.commit();
		
		
		
		/*long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			log.info("log输出" + i);
		}
		long time = System.currentTimeMillis() - start;
		log.info("所用时间" + time);*/
	}
	
	
}
