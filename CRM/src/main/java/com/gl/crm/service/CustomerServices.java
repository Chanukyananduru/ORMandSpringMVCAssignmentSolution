package com.gl.crm.service;

import java.util.List;

import com.gl.crm.entity.Customer;

public interface CustomerServices {

	public List<Customer> findAll();

	public Customer findById(int Id);

	public void save(Customer individual);

	public void deleteById(int Id);

}
