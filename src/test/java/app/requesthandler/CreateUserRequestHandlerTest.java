package app.requesthandler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import app.model.User;
import app.persistence.HibernateUserHelper;

public class CreateUserRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessCodeGivenUserCreationIsSuccessful() throws Exception {
		HibernateUserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		Mockito.doNothing().when(mockUserHelper).addUser(Mockito.any(User.class));
		
		CreateUserRequestHandler sut = new CreateUserRequestHandler(mockUserHelper);
		
		IndividualUserOperationRequestPayload individualUserOperationRequestPayload = new IndividualUserOperationRequestPayload(1, "test");
		Answer result = sut.process(individualUserOperationRequestPayload , null);
		
		assertEquals(201, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnErrorCodeGivenUserCreationFails() throws Exception {
		HibernateUserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		Mockito.doThrow(new Exception()).when(mockUserHelper).addUser(Mockito.any(User.class));
		
		CreateUserRequestHandler sut = new CreateUserRequestHandler(mockUserHelper);
		
		IndividualUserOperationRequestPayload individualUserOperationRequestPayload = new IndividualUserOperationRequestPayload(1, "test");
		Answer result = sut.process(individualUserOperationRequestPayload , null);
		
		assertEquals(500, result.getHttpCode());
	}

}
