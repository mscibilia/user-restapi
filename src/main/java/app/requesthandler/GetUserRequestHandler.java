package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.persistence.UserHelper;

public class GetUserRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	private final UserHelper userHelper;
	
	public GetUserRequestHandler() {
		this(UserHelper.getInstance());
	}
	
	
	/**
	 * Constructor for the purpose of injecting a UserHelper
	 * for testing purposes.
	 * 
	 * @param userHelper
	 */
	GetUserRequestHandler(UserHelper userHelper)	{
		super(EmptyRequestPayload.class);
		this.userHelper = userHelper;
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyRequestPayload, Map<String, String> urlParams) {
		Integer idToSearch = Integer.valueOf(urlParams.get(":id"));
		User user = userHelper.getUserById(idToSearch);
		
		if (user != null)	{
			return new Answer(200, gson.toJson(user));
		}	else	{
			return new Answer(404, "User with specified ID not found");
		}
	}


}
