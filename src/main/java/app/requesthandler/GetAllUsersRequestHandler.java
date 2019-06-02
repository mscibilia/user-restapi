package app.requesthandler;

import java.util.Map;

import app.repository.UserRepository;

public class GetAllUsersRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	private final UserRepository userRepository;
	
	public GetAllUsersRequestHandler(UserRepository userHelper) {
		super(EmptyRequestPayload.class);
		this.userRepository = userHelper;
	}
	

	@Override
	public Answer processImpl(EmptyRequestPayload emptyRequestPayload, Map<String, String> urlParams) {
		return new Answer(200, gson.toJson(userRepository.findAll()));
	}


}
