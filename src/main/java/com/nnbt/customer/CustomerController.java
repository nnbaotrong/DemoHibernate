package com.nnbt.customer;

import java.io.ObjectOutputStream.PutField;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public ModelAndView Index() {
		List<Customer> listCustomer = customerService.listAll();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listCustomer", listCustomer);
		return mv;
	}

	@RequestMapping("/new")
	public String newCustomer(Map<String, Object> model) {
		Customer customer = new Customer();
		model.put("customer", customer);
		return "new_customer";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.save(customer);

		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public ModelAndView editCustomer(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		Customer customer = customerService.get(id);
		mav.addObject("customer",customer);
		return mav;
	}
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam long id) {
		customerService.delete(id);
		return "redirect:/";
		
	}

}
