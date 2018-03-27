package app.persistence;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app.model.User;

public class UserHelper {
	
	private final SessionFactory sessionFactory;
	
	
	
	public UserHelper(SessionFactory sessionFactory)	{
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public List<User> getUserList()	{
		Session session = sessionFactory.openSession();
		
		Query<User> queryResult = session.createQuery("from User", User.class);
		List<User> resultList = queryResult.getResultList();
		session.close();
		
		return resultList;
	}
	
	public User getUserById(int id)	{
		Session session = sessionFactory.openSession();
		
		User result = session.get(User.class, id);
		
		session.close();
		
		return result;
	}
	
	public List<User> getUsersByName(String name)	{
		Session session = sessionFactory.openSession();
		
		List<User> result = session.createQuery("from User where name = '" + name + "'", User.class).getResultList();
		
		session.close();
		
		return result;
	}
	
	/**
     * Takes user attributes as parameters.
     * 
     * validates params and adds to user db if valid
     * 
     * @param idString
     * @param name
     * @return true if user created and added successfully
     * 
     */
	public boolean addUser(String idString, String name)	{
		boolean success = false;
		
		//Check specified parameter is a valid integer
		if (idString != null && idString.matches("[0-9]+")) {
			int id = Integer.valueOf(idString).intValue();
			
			addUser(new User(id, name));
			success = true;
		}
		return success;
	}


	/**
	 * Adds a new user to the user db
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try	{
			tx = session.beginTransaction();
			session.persist(user);
			tx.commit();
			System.out.println("User created");
			
		} catch(Exception e)	{	
			if (tx!=null) {
				tx.rollback();
			}
			
			throw e;
			
		} finally {
			session.close();
		}

	}
	
	/**
	 * Update a user in the database
	 * 
	 * @param incomingUser
	 * @return true if update was successful
	 */
	public boolean updateUser(User incomingUser)	{
		boolean success = false;
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try	{
			tx = session.beginTransaction();
			session.update(incomingUser);
			tx.commit();
			success= true;
			
		}	catch(Exception e)	{
			if(tx != null)	{
				tx.rollback();
			}
			throw e;
		} finally	{
			session.close();
		}
		
		
		return success;
	}
	
	/**
	 * Delete a user with the given ID from the database
	 * 
	 * @param idToDelete
	 * @return true, if user successfully deleted
	 * 		   false, if user to be deleted was not found
	 */
	public boolean deleteUser(int idToDelete) {
		boolean success = false;
		User userToDelete = getUserById(idToDelete);
		
		if(userToDelete != null)	{
			Session session = sessionFactory.openSession();
			Transaction tx = null;
			
			try	{
				tx = session.beginTransaction();
				session.delete(userToDelete);
				tx.commit();
				success = true;
				
			}	catch(Exception e)	{
				if(tx != null)	{
					tx.rollback();
				}
				throw e;
			} 
			
			finally	{
				session.close();
			}
			
		}
		return success;
	}


	@PreDestroy
	private void destroySessionFactory() 	{
		if(sessionFactory != null)	{
			sessionFactory.close();
		}
	}

}
