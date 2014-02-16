package prueba.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import prueba.dao.BaseDao;
import prueba.dao.Dao;
import prueba.service.BusinessService;
import prueba.service.BusinessServiceImpl;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages={"test.controller", "test.dao", "test.service"})
@Import(value={ThymeleafConfiguration.class})
public class AppConfiguration {

	private static String[] messageSourceBasenames = { "applicationResources" };

	
	@Bean
	public Dao dao() {
		return new BaseDao();
	}
	
	@Bean
	public BusinessService businessService() {
		return new BusinessServiceImpl(dao());
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames(messageSourceBasenames);
		return ms;
	}

}
