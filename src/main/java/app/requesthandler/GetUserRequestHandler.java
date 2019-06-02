package app.requesthandler;

import java.util.Map;
import java.util.NoSuchElementException;

import app.model.User;
import app.repository.UserRepository;

public class GetUserRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	private final UserRepository userRepository;
	
	public GetUserRequestHandler(UserRepository userHelper)	{
		super(EmptyRequestPayload.class);
		this.userRepository = userHelper;
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyRequestPayload, Map<String, String> urlParams) {
		Integer idToSearch = Integer.valueOf(urlParams.get(":id"));
		try	{
			User user = userRepository.findById(idToSearch).get();
			return new Answer(200, gson.toJson(user));
			
		} catch (NoSuchElementException e)	{
			return new Answer(404, "User with specified ID not found");
		}
		
	}


}
