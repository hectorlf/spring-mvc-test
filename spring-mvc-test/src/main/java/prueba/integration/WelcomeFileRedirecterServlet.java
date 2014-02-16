package prueba.integration;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class WelcomeFileRedirecterServlet implements Servlet {

	private ServletConfig servletConfig;
	
	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "WelcomeFileRedirecterServlet";
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig = servletConfig;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		if (!(arg1 instanceof HttpServletResponse)) throw new ServletException("Se ha recibido un ServletResponse que no es http, y no se puede responder la petici�n.");
		HttpServletResponse response = (HttpServletResponse)arg1;
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.sendRedirect(arg0.getServletContext().getContextPath() + "/index.page");
	}

}