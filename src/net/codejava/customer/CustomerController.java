package net.codejava.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import rest.Alien;
import rest.Consome;

@Controller

public class CustomerController {
	String msg = "<";

	@Autowired
	private CustomerService customerService;
	
		
	@RequestMapping("/")
	public ModelAndView home(HttpSession session, HttpServletRequest req) {
		System.out.println("em home -" + session.getAttribute("usuarioLogado"));
		if (session.getAttribute("usuarioLogado") == null) {
			ModelAndView mav = new ModelAndView("login");
			return mav;
		}
		if (msg.equals("<")) {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			msg = u.getUsuario();
		}
		msg = " " +req.getParameter("msg");
		System.out.println("tamanho de msg - " + msg.length());
		System.out.println("Que merda!");
		System.out.println(msg);
		List<Customer> listCustomer = customerService.listAll();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("listCustomer", listCustomer);
		
		if (!(msg.equals(" null"))) {
		mav.addObject("msg", msg);
		}
		//System.out.println(msg);
		return mav;
	}
	
	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
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
	public ModelAndView editCustomerForm(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		Customer customer = customerService.get(id);
		mav.addObject("customer", customer);
		
		return mav;
	}
	
	@RequestMapping("/delete")
	public String deleteCustomerForm(@RequestParam long id) {
		customerService.delete(id);
		return "redirect:/";		
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Customer> result = customerService.search(keyword);
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("result", result);
		
		return mav;		
	}	
	//============================REST=================================
	@RequestMapping("/rest")
	public ModelAndView getRest() {
		System.out.println("Estou em getRest!");
		Consome con = new Consome();
        Alien[] a =  con.getList();
		ModelAndView mav = new ModelAndView("stdlist");
		mav.addObject("data", a);
		return mav;
	}
	@RequestMapping("/restnovo")
	public String newAlienForm(Map<String, Object> model) {
		Alien a = new Alien();
		model.put("data", a);
		return "new_alien";
	}

	/*
	 * @RequestMapping("/resterro") public ModelAndView errRest(@RequestParam int
	 * err, Model model) {
	 * 
	 * System.out.println("Estou em errRest!"); ModelAndView mav = new
	 * ModelAndView("erro"); mav.addObject("data", err); return mav; }
	 */
	@RequestMapping("/restedit")
	public ModelAndView editAlienForm(@RequestParam int id) {
		ModelAndView mav = new ModelAndView("edit_alien");
		Consome con = new Consome();
        Alien a =  con.getAlien(id);
		mav.addObject("data", a);
		
		return mav;
	}
	@RequestMapping(value = "/restsave", method = RequestMethod.POST)
	public ModelAndView saveAlien(@ModelAttribute("data") Alien alien) throws JsonProcessingException {
		Consome con = new Consome();
		int i = con.updAlien(alien);
		String msg = "Código de erro proveniente de SAVE - " + i;
		
		  if (i == 200) {
			  ModelAndView mav = new ModelAndView("redirect:/rest");
			  return mav;
			  //return "redirect:/rest"; 
			  }
		 
		ModelAndView mav = new ModelAndView("edit_alien");
		mav.addObject("data", alien);
		mav.addObject("msg", msg);
		// model.addAttribute("data", alien);
		 //  return "redirect:/resterro/" + i;
		return mav;
	}
	@RequestMapping(value = "/restadd", method = RequestMethod.POST)
	public ModelAndView addAlien(@ModelAttribute("data") Alien alien) throws JsonProcessingException {
		Consome con = new Consome();
		int i = con.addAlien(alien);
		String msg = "Código de erro proveniente de ADD - " + i;
		System.out.println("código de retorno de add - " + i);
		  if (i == 200) {
			  ModelAndView mav = new ModelAndView("redirect:/rest");
			  return mav;
			  //return "redirect:/rest"; 
			  }
		 
		ModelAndView mav = new ModelAndView("new_alien");
		mav.addObject("data", alien);
		mav.addObject("msg", msg);
		// model.addAttribute("data", alien);
		 //  return "redirect:/resterro/" + i;
		return mav;
	}


	@RequestMapping("/restdelete")
	public String delAlien(@RequestParam int id) {
		Consome con = new Consome();
		int i = con.delAlien(id);
		return "redirect:/rest";		
	}
	
}
	