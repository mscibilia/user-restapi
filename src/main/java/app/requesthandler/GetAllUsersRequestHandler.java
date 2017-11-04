package app.requesthandler;

import java.util.Map;

import app.persistence.UserHelper;

public class GetAllUsersRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	public GetAllUsersRequestHandler(Class<EmptyRequestPayload> requestPayload) {
		super(requestPayload);
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyRequestPayload, Map<String, String> urlParams) {
		return new Answer(200, gson.toJson(UserHelper.getInstance().getUserList()));
	}


}
