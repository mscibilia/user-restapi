package app.requesthandler;

/**
 * Generic request payload for an operation (get, update, delete) on a single 'User' entity.
 */
public class IndividualUserOperationRequestPayload implements Validable {
	
	private int userId;
	
	private String userName;
	
	/**
	 * @param user
	 */
	public IndividualUserOperationRequestPayload(int id, String name) {
		this.userId = id;
		this.userName = name;
	}


	public int getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}

	@Override
	public boolean isValid() {
		return userName != null;
	}
}
