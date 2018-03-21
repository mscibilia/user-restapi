package app.requesthandler;

import java.util.Map;

import app.persistence.UserHelper;

public class GetAllUsersRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	private final UserHelper userHelper;
	
	public GetAllUsersRequestHandler() {
		this(UserHelper.getInstance());
	}
	
	/**
	 * Constructor for the purpose of injecting a UserHelper
	 * for testing purposes.
	 * 
	 * @param userHelper
	 */
	GetAllUsersRequestHandler(UserHelper userHelper) {
		super(EmptyRequestPayload.class);
		this.userHelper = userHelper;
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyRequestPayload, Map<String, String> urlParams) {
		return new Answer(200, gson.toJson(userHelper.getUserList()));
	}


}
