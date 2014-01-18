package prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import prueba.service.BusinessService;

@Controller
public class IndexController {

	@Autowired
	private BusinessService service;

	@RequestMapping(value="/index.page")
	public String welcome(ModelMap model) {
		model.addAttribute("name", service.getWorldName());
		return "/index.html";
	}

	public void setService(BusinessService service) {
		this.service = service;
	}

}
