package prueba.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import prueba.model.Message;
import prueba.service.BusinessService;

@Controller
public class IndexController {

	final static Logger logger = LoggerFactory.getLogger(IndexController.class);

	private BusinessService businessService;

	@Inject
	public IndexController(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	
	@RequestMapping(value="/index.action")
	public String welcome(ModelMap model) {
		logger.debug("Entrando a IndexController.welcome()");
		model.addAttribute("messages", businessService.getAllMessages());
		logger.debug("Retornando vista a presentar");
		return "/index.html";
	}

	@RequestMapping(value="/salute.action")
	public String salute(@Valid @ModelAttribute("message") Message message, BindingResult bindingResult, ModelMap model) {
		logger.debug("Entrando a IndexController.salute()");
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.error(error.getCode() + " " + error.getObjectName() + " " + error.getDefaultMessage());
			}
			return "/error.html";
		}
		return "/salute.html";
	}

	@RequestMapping(value="/secure.page")
	public String secure(ModelMap model) {
		logger.debug("Entrando a IndexController.secure()");
		return "/secure.html";
	}

	@RequestMapping(value="/unauthorized.page")
	public String unauthorized(ModelMap model) {
		logger.debug("Entrando a IndexController.unauthorized()");
		return "/unauthorized.html";
	}

}
