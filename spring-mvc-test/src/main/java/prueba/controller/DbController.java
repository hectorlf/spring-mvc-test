package prueba.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import prueba.service.BusinessService;

@Controller
public class DbController {

	final static Logger logger = LoggerFactory.getLogger(DbController.class);

	private BusinessService businessService;

	@Inject
	public DbController(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	
	@RequestMapping(value="/db.page")
	public String show(ModelMap model) {
		logger.debug("Entrando a DbController.show()");
		model.addAttribute("messages", businessService.getAllMessages());
		logger.debug("Retornando vista a presentar");
		return "/db.html";
	}

}
