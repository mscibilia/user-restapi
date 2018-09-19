package app;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import app.requesthandler.CreateUserRequestHandler;
import app.requesthandler.DeleteUserRequestHandler;
import app.requesthandler.GetAllUsersRequestHandler;
import app.requesthandler.GetUserRequestHandler;
import app.requesthandler.UpdateUserRequestHandler;

public class App {
	
	
    public static void main(String[] args) {
    	
    	@SuppressWarnings("resource")
		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    	((AbstractApplicationContext) applicationContext).registerShutdownHook();
        
		get("/users", applicationContext.getBean(GetAllUsersRequestHandler.class));
        
		get("/users/:id", applicationContext.getBean(GetUserRequestHandler.class));
		
		post("/users/create", "application/json", applicationContext.getBean(CreateUserRequestHandler.class));
		
		put("/users/update","application/json", applicationContext.getBean(UpdateUserRequestHandler.class));
		
		delete("/users/:id", applicationContext.getBean(DeleteUserRequestHandler.class));
		
    }
    
    
}