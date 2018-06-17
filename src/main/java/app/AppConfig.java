package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import app.persistence.HibernateUserHelper;
import app.persistence.UserHelper;
import app.requesthandler.CreateUserRequestHandler;
import app.requesthandler.DeleteUserRequestHandler;
import app.requesthandler.GetAllUsersRequestHandler;
import app.requesthandler.GetUserRequestHandler;
import app.requesthandler.UpdateUserRequestHandler;

@Configuration
public class AppConfig {

	@Bean(name="userHelper")
	@Scope("singleton")
	public UserHelper getUserHelper()	{
		return new HibernateUserHelper(new org.hibernate.cfg.Configuration().configure().buildSessionFactory());
	}
	
	@Bean(name="getAllUsersRequestHandler")
	@Scope("prototype")
	public GetAllUsersRequestHandler getGetAllUsersRequestHandler()	{
		return new GetAllUsersRequestHandler(getUserHelper());
	}
	
	@Bean(name="getUserRequestHandler")
	@Scope("prototype")
	public GetUserRequestHandler getGetUserRequestHandler()	{
		return new GetUserRequestHandler(getUserHelper());
	}
	
	@Bean(name="createUserRequestHandler")
	@Scope("prototype")
	public CreateUserRequestHandler getCreateUserRequestHandler()	{
		return new CreateUserRequestHandler(getUserHelper());
	}
	
	@Bean(name="updateUserRequestHandler")
	@Scope("prototype")
	public UpdateUserRequestHandler getUpdateUserRequestHandler()	{
		return new UpdateUserRequestHandler(getUserHelper());
	}
	
	@Bean(name="deleteUserRequestHandler")
	@Scope("prototype")
	public DeleteUserRequestHandler getDeleteUserRequestHandler()	{
		return new DeleteUserRequestHandler(getUserHelper());
	}
	
}
