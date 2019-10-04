package app.requesthandler;

import java.util.Map;

import app.repository.UserRepository;

public class DeleteUserRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	private final UserRepository userRepository;
	
	public DeleteUserRequestHandler(UserRepository userHelper) {
		super(EmptyRequestPayload.class);
		this.userRepository = userHelper;
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyUserRequestPayload, Map<String, String> urlParams) {
		
		Integer idOfUserToDelete = Integer.valueOf(urlParams.get(":id"));
		
		try {
			userRepository.deleteById(idOfUserToDelete);
			return new Answer(200, "User deleted successfully");
			
		} catch (Exception e)	{
			return new Answer(500, "User deletion failed: Server Error");
		}
	}


}
