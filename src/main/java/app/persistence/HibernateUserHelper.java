package app.persistence;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app.model.User;

public class HibernateUserHelper implements UserHelper {
	
	private final SessionFactory sessionFactory;
	
	
	
	public HibernateUserHelper(SessionFactory sessionFactory)	{
		this.sessionFactory = sessionFactory;
	}
	
	
	
	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#getUserList()
	 */
	@Override
	public List<User> getUserList()	{
		Session session = sessionFactory.openSession();
		
		Query<User> queryResult = session.createQuery("from User", User.class);
		List<User> resultList = queryResult.getResultList();
		session.close();
		
		return resultList;
	}
	
	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#getUserById(int)
	 */
	@Override
	public User getUserById(int id)	{
		Session session = sessionFactory.openSession();
		
		User result = session.get(User.class, id);
		
		session.close();
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#getUsersByName(java.lang.String)
	 */
	@Override
	public List<User> getUsersByName(String name)	{
		Session session = sessionFactory.openSession();
		
		List<User> result = session.createQuery("from User where name = '" + name + "'", User.class).getResultList();
		
		session.close();
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#addUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addUser(String idString, String name)	{
		boolean success = false;
		
		//Check specified parameter is a valid integer
		if (idString != null && idString.matches("[0-9]+")) {
			int id = Integer.valueOf(idString).intValue();
			
			try {
				addUser(new User(id, name));
			} catch (Exception e) {
				success = false;
			}
			
			success = true;
		}
		return success;
	}


	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#addUser(app.model.User)
	 */
	@Override
	public void addUser(User user) throws Exception {
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
	
	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#updateUser(app.model.User)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see app.persistence.UserHelper#deleteUser(int)
	 */
	@Override
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
