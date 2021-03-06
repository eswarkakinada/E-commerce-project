package com.niit.shoppingcart.dao;

import java.util.List;

import org.hibernate.Query;

//import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.UserDetails;

@EnableTransactionManagement
@Repository(value = "userDetailsDAO")
public class UserDetailsDAOImpl implements UserDetailsDAO {

	public UserDetailsDAOImpl() {
		System.out.println("in userDAO Impl");
	}

	@Autowired
	private SessionFactory sessionFactory;

	public UserDetailsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
	
	@Transactional
	public boolean save(UserDetails userDetails) {
		try {
			 sessionFactory.getCurrentSession().save(userDetails);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean update(UserDetails userDetails) {
		try {
			sessionFactory.getCurrentSession().update(userDetails);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean delete(UserDetails userDetails) {
		try {
			sessionFactory.getCurrentSession().delete(userDetails);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public UserDetails get(String id) {

		String hql = "from UserDetails where id=" + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<UserDetails> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}

	@Transactional
	public UserDetails isValidUser(String id, String password) {

		// select * from UserDetails whre id='101' and Password 'niit'
		String hql = "From UserDetails whre id= '" + id + "'and password= '" + password + "'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		List<UserDetails> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}

	}

	@Transactional
	public List<UserDetails> list() {
		String hql = "from product";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
