package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.persistence.UserHelper;

public class CreateUserRequestHandler extends AbstractRequestHandler<IndividualUserOperationRequestPayload> {

	public CreateUserRequestHandler(Class<IndividualUserOperationRequestPayload> requestPayload) {
		super(requestPayload);
	}

	@Override
	public Answer processImpl(IndividualUserOperationRequestPayload createUserRequestPayload, Map<String, String> urlParams) {
		try {
			User incomingUser = createUserRequestPayload.getUser();
			UserHelper.getInstance().addUser(incomingUser);
			return new Answer(201, "User creation successful: " + incomingUser.toString());
		} catch (Exception e) {
			return new Answer(500, "User creation failed: Server Error");
		}
	}


}
