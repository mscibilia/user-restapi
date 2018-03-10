package app.requesthandler;

/**
 * Generic request payload for an operation (get, update, delete) on a single 'User' entity.
 */
public class IndividualUserOperationRequestPayload implements Validable {
	
	private int id;
	
	private String name;
	
	/**
	 * @param user
	 */
	public IndividualUserOperationRequestPayload(int id, String name) {
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean isValid() {
		return name != null;
	}
}
