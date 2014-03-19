package prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value="/login-form.page")
	public String login(ModelMap model) {
		logger.debug("Entrando a login()");
		return "/login-form.html";
	}

}
