package prueba.integration;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationInitializerListener implements ServletContextListener {

	final static Logger logger = LoggerFactory.getLogger(ApplicationInitializerListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/testds");
			Connection conn = ds.getConnection();
			Statement s = conn.createStatement();
			s.execute("create table messages (id INTEGER PRIMARY KEY, text VARCHAR(20))");
			s.execute("insert into messages values (1, 'Pepe')");
			s.execute("insert into messages values (2, 'Maria')");
			conn.commit();
			conn.close();
		} catch(Exception e) {
			logger.error("No se ha podido crear la base de datos");
			throw new RuntimeException(e);
		}
	}

}
