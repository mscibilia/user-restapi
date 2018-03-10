package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.persistence.UserHelper;

public class CreateUserRequestHandler extends AbstractRequestHandler<IndividualUserOperationRequestPayload> {

	public CreateUserRequestHandler() {
		super(IndividualUserOperationRequestPayload.class);
	}

	@Override
	public Answer processImpl(IndividualUserOperationRequestPayload createUserRequestPayload, Map<String, String> urlParams) {
		try {
			User incomingUser = new User(createUserRequestPayload.getId(), createUserRequestPayload.getName());
			UserHelper.getInstance().addUser(incomingUser);
			return new Answer(201, "User creation successful: " + incomingUser.toString(), false);
		} catch (Exception e) {
			return new Answer(500, "User creation failed: Server Error", false);
		}
	}


}
