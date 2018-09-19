package app.persistence;

import java.util.List;

import app.model.User;

public interface UserHelper {

	List<User> getUserList();

	User getUserById(int id);

	List<User> getUsersByName(String name);

	/**
	 * Takes user attributes as parameters.
	 * 
	 * validates params and adds to user db if valid
	 * 
	 * @param idString
	 * @param name
	 * @return true if user created and added successfully
	 * 
	 * @deprecated use {@link #addUser(User)} instead
	 * 
	 */
	boolean addUser(String idString, String name);

	/**
	 * Adds a new user to the user db
	 * 
	 * @param user
	 */
	void addUser(User user) throws Exception;

	/**
	 * Update a user in the database
	 * 
	 * @param incomingUser
	 * @return true if update was successful
	 */
	boolean updateUser(User incomingUser);

	/**
	 * Delete a user with the given ID from the database
	 * 
	 * @param idToDelete
	 * @return true, if user successfully deleted
	 * 		   false, if user to be deleted was not found
	 */
	boolean deleteUser(int idToDelete);

}