package app.requesthandler;

import java.util.Map;

import app.persistence.UserHelper;

public class DeleteUserRequestHandler extends AbstractRequestHandler<EmptyRequestPayload> {

	public DeleteUserRequestHandler() {
		super(EmptyRequestPayload.class);
	}

	@Override
	public Answer processImpl(EmptyRequestPayload emptyUserRequestPayload, Map<String, String> urlParams) {
		
		Integer idOfUserToDelete = Integer.valueOf(urlParams.get(":id"));
		
		try {
			
			if(UserHelper.getInstance().deleteUser(idOfUserToDelete))	{
				return new Answer(200, "User deleted successfully");
			}	else	{
				return new Answer(404, "User with specified ID not found");
			}
			
		} catch (Exception e)	{
			return new Answer(500, "User deletion failed: Server Error");
		}
	}


}
