package app.requesthandler;

import java.util.Map;

import app.model.User;
import app.persistence.UserHelper;

public class UpdateUserRequestHandler extends AbstractRequestHandler<IndividualUserOperationRequestPayload> {

	public UpdateUserRequestHandler(Class<IndividualUserOperationRequestPayload> requestPayload) {
		super(requestPayload);
	}

	@Override
	public Answer processImpl(IndividualUserOperationRequestPayload updateUserRequestPayload, Map<String, String> urlParams) {
		try {
			User incomingUser = new User(updateUserRequestPayload.getUserId(), updateUserRequestPayload.getUserName());
			if(UserHelper.getInstance().updateUser(incomingUser))	{
				return new Answer(200, "User update successful: " + incomingUser.toString(), false);
			}	else	{
				return new Answer(404, "User update failed: User not found", false);
			}
			
		} catch (Exception e) {
			return new Answer(500, "User update failed: Server Error", false);
		}
	}


}
