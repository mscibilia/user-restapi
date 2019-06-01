package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.repository.UserRepository;

public class CreateUserRequestHandler extends AbstractRequestHandler<IndividualUserOperationRequestPayload> {

	private final UserRepository userHelper;
	
	public CreateUserRequestHandler(UserRepository userHelper) {
		super(IndividualUserOperationRequestPayload.class);
		this.userHelper = userHelper;
	}

	@Override
	public Answer processImpl(IndividualUserOperationRequestPayload createUserRequestPayload, Map<String, String> urlParams) {
		try {
			User incomingUser = new User(createUserRequestPayload.getId(), createUserRequestPayload.getName());
			userHelper.save(incomingUser);
			return new Answer(201, "User creation successful: " + incomingUser.toString());
		} catch (Exception e) {
			return new Answer(500, "User creation failed: Server Error");
		}
	}


}
