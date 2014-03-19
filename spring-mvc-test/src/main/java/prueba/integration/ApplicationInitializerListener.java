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

	private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializerListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/testds");
			Connection conn = ds.getConnection();
			Statement s = conn.createStatement();
			s.execute("create table messages (id INTEGER PRIMARY KEY, text VARCHAR(20))");
			s.execute("insert into messages values (1, 'Mensaje 1')");
			s.execute("insert into messages values (2, 'Mensaje 2')");
			s.execute("create table users(username varchar_ignorecase(50) not null primary key, password varchar_ignorecase(50) not null, enabled boolean not null)");
			s.execute("create table authorities (username varchar_ignorecase(50) not null, authority varchar_ignorecase(50) not null, constraint fk_authorities_users foreign key(username) references users(username))");
			s.execute("create unique index ix_auth_username on authorities (username,authority)");
			s.execute("insert into users values ('pepe', 'pepe', true)");
			s.execute("insert into users values ('paco', 'paco', true)");
			s.execute("insert into authorities values ('pepe', 'ROLE_ADMIN')");
			s.execute("insert into authorities values ('pepe', 'ROLE_USER')");
			s.execute("insert into authorities values ('paco', 'ROLE_USER')");
			conn.commit();
			conn.close();
		} catch(Exception e) {
			logger.error("No se ha podido crear la base de datos");
			throw new RuntimeException(e);
		}
	}

}
