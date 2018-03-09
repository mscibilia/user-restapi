package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.persistence.UserHelper;

public class GetUserRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	public GetUserRequestHandler(Class<EmptyRequestPayload> requestPayload) {
		super(requestPayload);
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyRequestPayload, Map<String, String> urlParams) {
		Integer idToSearch = Integer.valueOf(urlParams.get(":id"));
		User user = UserHelper.getInstance().getUserById(idToSearch);
		
		if (user != null)	{
			return new Answer(200, gson.toJson(user), true);
		}	else	{
			return new Answer(404, "User with specified ID not found", false);
		}
	}


}
