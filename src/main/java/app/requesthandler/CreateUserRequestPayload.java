package app.requesthandler;

import app.model.User;

public class CreateUserRequestPayload implements Validable {
	
	private User user;
	
	/**
	 * @param user
	 */
	public CreateUserRequestPayload(User user) {
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
