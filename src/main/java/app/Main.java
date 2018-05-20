package app;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.google.gson.Gson;

import app.model.User;
import app.persistence.UserHelper;
import app.requesthandler.CreateUserRequestHandler;
import app.requesthandler.DeleteUserRequestHandler;
import app.requesthandler.GetAllUsersRequestHandler;
import app.requesthandler.GetUserRequestHandler;
import app.requesthandler.UpdateUserRequestHandler;

public class Main {
	
	
    public static void main(String[] args) {
    	
    	@SuppressWarnings("resource")
		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    	((AbstractApplicationContext) applicationContext).registerShutdownHook();
        
		final UserHelper userHelper = applicationContext.getBean(UserHelper.class);
        
		get("/users", new GetAllUsersRequestHandler(userHelper));
        
        
		get("/users/:id", new GetUserRequestHandler(userHelper));
		
		//Either implement this cleaner or get rid of it, Does it add value?
		get("/users/byName/:name", (req, res) -> {
			String nameToSearch = req.params(":name");
			List<User> users = userHelper.getUsersByName(nameToSearch);
			
			if (users != null)	{
				Gson gson = new Gson();
				res.type("application/json");
				res.status(200);
				return gson.toJson(users);
				
			}	else	{
				res.status(404);
				return "User(s) with specified name not found";
			}
			
		});

		
		post("/users/create", "application/json", new CreateUserRequestHandler(userHelper));
		
		put("/users/update","application/json", new UpdateUserRequestHandler(userHelper));
		
		delete("/users/:id", new DeleteUserRequestHandler(userHelper));
		
    }
    
    
}