package prueba.controller;

import java.util.List;

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

@Controller
public class ParamsController {

	final static Logger logger = LoggerFactory.getLogger(ParamsController.class);

	@RequestMapping(value="/salute.page")
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

}
