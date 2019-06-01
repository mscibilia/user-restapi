package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.repository.UserRepository;

public class UpdateUserRequestHandler extends AbstractRequestHandler<IndividualUserOperationRequestPayload> {

	private final UserRepository userRepository;
	
	public UpdateUserRequestHandler(UserRepository userHelper) {
		super(IndividualUserOperationRequestPayload.class);
		this.userRepository = userHelper;
	}

	@Override
	public Answer processImpl(IndividualUserOperationRequestPayload updateUserRequestPayload, Map<String, String> urlParams) {
		try {
			User incomingUser = new User(updateUserRequestPayload.getId(), updateUserRequestPayload.getName());
			userRepository.save(incomingUser);
			return new Answer(200, "User update successful: " + incomingUser.toString());
			//return new Answer(404, "User update failed: User not found");
			
		} catch (Exception e) {
			return new Answer(500, "User update failed: Server Error");
		}
	}


}
