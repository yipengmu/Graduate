package com.laomu.graduate.database;

import java.io.UnsupportedEncodingException;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;

import com.laomu.graduate.database.dao.Student;

public class HibernateManager {

	public HibernateManager() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Student s = new Student();
		try {
			s.setName(new String("老穆".getBytes(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.setAge(8);

		org.hibernate.SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
	}

}
