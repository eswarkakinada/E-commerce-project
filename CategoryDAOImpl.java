package com.niit.shoppingcart.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Category;

@EnableTransactionManagement
@Repository(value = "categoryDAO")

public class CategoryDAOImpl implements CategoryDAO {

	// private static final Query SessionFactory = null;
	@Autowired
	private SessionFactory sessionFactory;

	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	@Transactional
	public boolean save(Category category) {
		try {
			sessionFactory.getCurrentSession().save(category);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch blockry
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean update(Category category) {
		try {

			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch blockry
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean delete(Category category) {
		try {

			sessionFactory.getCurrentSession().delete(category);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch blockry
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public Category get(String id) {
		System.out.println("1");
		String hql = "from Category where id='" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Category> list = query.list();
		System.out.println("2");
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}

	@Transactional
	public Category getByName(String name) {

		System.out.println("category name");
		String hql = "from Category where name=" + "'" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Category> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}

	@Transactional
	public List<Category> list() {
System.out.println("nill");
		String hql = "from Category";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println(hql);

		return query.list();

	}
}
