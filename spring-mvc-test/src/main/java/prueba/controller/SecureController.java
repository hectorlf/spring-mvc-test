package prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecureController {

	final static Logger logger = LoggerFactory.getLogger(SecureController.class);

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
