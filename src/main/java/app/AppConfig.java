package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import app.persistence.HibernateUserHelper;
import app.persistence.UserHelper;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
	public UserHelper userHelper()	{
		return new HibernateUserHelper(new org.hibernate.cfg.Configuration().configure().buildSessionFactory());
	}
	
}
