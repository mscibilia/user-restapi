package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import app.repository.UserRepository;
import app.requesthandler.CreateUserRequestHandler;
import app.requesthandler.DeleteUserRequestHandler;
import app.requesthandler.GetAllUsersRequestHandler;
import app.requesthandler.GetUserRequestHandler;
import app.requesthandler.UpdateUserRequestHandler;

@Configuration
@Import(JpaConfig.class)
public class AppConfig {

	@Autowired
	private UserRepository userRepository;
	
	@Bean(name="getAllUsersRequestHandler")
	@Scope("prototype")
	public GetAllUsersRequestHandler getGetAllUsersRequestHandler()	{
		return new GetAllUsersRequestHandler(userRepository);
	}
	
	@Bean(name="getUserRequestHandler")
	@Scope("prototype")
	public GetUserRequestHandler getGetUserRequestHandler()	{
		return new GetUserRequestHandler(userRepository);
	}
	
	@Bean(name="createUserRequestHandler")
	@Scope("prototype")
	public CreateUserRequestHandler getCreateUserRequestHandler()	{
		return new CreateUserRequestHandler(userRepository);
	}
	
	@Bean(name="updateUserRequestHandler")
	@Scope("prototype")
	public UpdateUserRequestHandler getUpdateUserRequestHandler()	{
		return new UpdateUserRequestHandler(userRepository);
	}
	
	@Bean(name="deleteUserRequestHandler")
	@Scope("prototype")
	public DeleteUserRequestHandler getDeleteUserRequestHandler()	{
		return new DeleteUserRequestHandler(userRepository);
	}
	
}
