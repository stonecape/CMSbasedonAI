package com.bistu.cmsbai.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static String CONFIG_FILE_LOCATION = "com/bistu/cmsbai/config/hibernate.cfg.xml";
    private  static Configuration configuration = new Configuration();    
    private static SessionFactory sessionFactory;

	static {
    	try {
    		sessionFactory = configuration.configure(CONFIG_FILE_LOCATION).buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
    }

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void main(String[] args) {
		System.out.println(getSessionFactory());
		/*Session session = getSessionFactory().openSession();
		String queryAllTickets = "from Ticket t where t.startTime > ?";
		Query query = session.createQuery(queryAllTickets);
		query.setDate(0, new Date());
		List<Ticket> list = query.list();
		for(Ticket t : list) {
			System.out.println(t);
		}*/
	}
}