package app.requesthandler;

import app.model.User;

/**
 * Generic request payload for an operation (get, update, delete) on a single 'User' entity.
 */
public class IndividualUserOperationRequestPayload implements Validable {
	
	private User user;
	
	/**
	 * @param user
	 */
	public IndividualUserOperationRequestPayload(User user) {
		this.user = user;
	}


	public User getUser() {
		return user;
	}

	@Override
	public boolean isValid() {
		return user != null && user.getName() != null;
	}
}
