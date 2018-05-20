package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import app.persistence.HibernateUserHelper;
import app.persistence.UserHelper;

@Configuration
public class AppConfig {

	@Bean(name="userHelper")
	@Scope("singleton")
	public UserHelper getUserHelper()	{
		return new HibernateUserHelper(new org.hibernate.cfg.Configuration().configure().buildSessionFactory());
	}
	
}
