package app;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import app.persistence.UserHelper;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
	public UserHelper userHelper()	{
		return new UserHelper(sessionFactory());
	}
	
	@Bean
	@Scope("singleton")
	public SessionFactory sessionFactory()	{
		return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
	}
}
