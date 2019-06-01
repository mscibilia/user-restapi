package app.requesthandler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import app.model.User;
import app.repository.UserRepository;

public class CreateUserRequestHandlerTest {
	

	@Test
	public void processShouldReturnSuccessCodeGivenUserCreationIsSuccessful() throws Exception {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		Mockito.doNothing().when(mockUserRepository).save(Mockito.any(User.class));
		
		CreateUserRequestHandler sut = new CreateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload individualUserOperationRequestPayload = new IndividualUserOperationRequestPayload(1, "test");
		Answer result = sut.process(individualUserOperationRequestPayload , null);
		
		assertEquals(201, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnErrorCodeGivenUserCreationFails() throws Exception {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		Mockito.doThrow(new Exception()).when(mockUserRepository).save(Mockito.any(User.class));
		
		CreateUserRequestHandler sut = new CreateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload individualUserOperationRequestPayload = new IndividualUserOperationRequestPayload(1, "test");
		Answer result = sut.process(individualUserOperationRequestPayload , null);
		
		assertEquals(500, result.getHttpCode());
	}

}
