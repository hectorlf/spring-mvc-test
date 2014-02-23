package prueba.integration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.web.context.ContextLoaderListener;

public class TransactionFilter implements Filter {

    public TransactionFilter() {
    }


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ApplicationContext ctx = ContextLoaderListener.getCurrentWebApplicationContext();
		PlatformTransactionManager txManager = (PlatformTransactionManager)ctx.getBean("txManager");
		TransactionAttribute def = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus tx = txManager.getTransaction(def);
		chain.doFilter(request, response);
		if (tx.isCompleted()) System.out.println("La transaccion ya está cerrada");
		txManager.rollback(tx);
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
