package com.gl.crm.controller;

import java.util.List;

import com.gl.crm.entity.Customer;
import com.gl.crm.service.CustomerServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServices customerservice;

	@RequestMapping("/list") //"/list"
	public String listCustomer(Model theModel) {

		System.out.println("Request to list all customer received");
		List<Customer> allCustomers = customerservice.findAll();
		theModel.addAttribute("Customers", allCustomers);

		return "list-customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customer individual = new Customer();
		theModel.addAttribute("Customer", individual);

		return "customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int Id, Model theModel) {
		Customer individual = customerservice.findById(Id);
		theModel.addAttribute("Customer", individual);
		return "customer-form";

	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int Id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		Customer individual;
		if (Id != 0) {
			individual = customerservice.findById(Id);
			individual.setFirstName(firstName);
			individual.setLastName(lastName);
			individual.setEmail(email);
		} else
			individual = new Customer(firstName, lastName, email);

		customerservice.save(individual);

		return "redirect:/customer/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int Id) {
		customerservice.deleteById(Id);
		return "redirect:/customer/list";

	}

}
