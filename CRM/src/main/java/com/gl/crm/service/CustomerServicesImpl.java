package com.gl.crm.service;

import java.util.List;

import com.gl.crm.entity.Customer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerServicesImpl implements CustomerServices {

	private SessionFactory sessionFactory;
	private Session session;

	// Constructor to start sessionFactory
	@Autowired
	CustomerServicesImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Customer> findAll() {

		Transaction trans = session.beginTransaction();

		List<Customer> customers = session.createQuery("from Customer",Customer.class).list();

		trans.commit();

		return customers;
	}

	@Transactional
	public Customer findById(int Id) {
		Customer customer = new Customer();

		Transaction trans = session.beginTransaction();

		customer = session.get(Customer.class, Id);

		trans.commit();

		return customer;
	}

	@Transactional
	public void save(Customer individual) {
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(individual);
		trans.commit();

	}

	@Override
	public void deleteById(int Id) {
		Customer customer = new Customer();
		
		Transaction trans = session.beginTransaction();

		customer = session.get(Customer.class, Id);
		
		session.delete(customer);
		trans.commit();

	}

}
