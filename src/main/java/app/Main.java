package app;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.google.gson.Gson;

import app.model.User;
import app.persistence.UserHelper;

public class Main {
	
	
    public static void main(String[] args) {
        
        get("/users", (req, res) -> {
        	Gson gson = new Gson();
        	res.type("application/json");
        	res.status(200);
        	return gson.toJson(UserHelper.getInstance().getUserList());
        });
        
        
		get("/users/:id", (req, res) -> {
			Integer idToSearch = Integer.valueOf(req.params(":id"));
			User user = UserHelper.getInstance().getUserById(idToSearch);
			
			if (user != null)	{
				Gson gson = new Gson();
				res.type("application/json");
				res.status(200);
				return gson.toJson(user);
				
			}	else	{
				res.status(400);
				return "User with specified ID not found";
			}
			
		});
		
		//Either implement this cleaner or get rid of it, Does it add value?
		get("/users/byName/:name", (req, res) -> {
			String nameToSearch = req.params(":name");
			List<User> users = UserHelper.getInstance().getUsersByName(nameToSearch);
			
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

		
		post("/users/create", "application/json", (req, res) -> {
			Gson gson = new Gson();
			String responseMessage = null;
			User incomingUser = gson.fromJson(req.body(), User.class);
			try {
				UserHelper.getInstance().addUser(incomingUser);
				res.status(201);
				responseMessage = "User creation successful: " + incomingUser.toString();
			} catch (Exception e) {
				res.status(500);
				responseMessage = "User creation failed: Server Error";
			}

			return responseMessage;

		});
		
		put("/users/update","application/json", (req, res) -> {
			Gson gson = new Gson();
			String responseMessage = null;
			User incomingUser = gson.fromJson(req.body(), User.class);
			if (UserHelper.getInstance().updateUser(incomingUser)) {
				res.status(200);
				responseMessage = "User update successful" + incomingUser.toString();
			} else {
				res.status(400);
				responseMessage = "User update failed: User not found";
			}

			return responseMessage;

		});
		
		delete("/users/:id", (req, res) -> {
			Integer idToDelete = Integer.valueOf(req.params(":id"));
			
			String responseMessage = null;
			if (UserHelper.getInstance().deleteUser(idToDelete)) {
				res.status(200);
				responseMessage = "User deleted successfully";
			} else {
				res.status(404);
				responseMessage = "User with specified ID not found";
			}
			
			return responseMessage;
		});
    }
    
    
}