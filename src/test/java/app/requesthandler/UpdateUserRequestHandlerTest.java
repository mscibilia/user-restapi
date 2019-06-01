package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import app.model.User;
import app.repository.UserRepository;

public class UpdateUserRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessCodeGivenUpdateIsSuccessful() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		//Mockito.when(mockUserRepository.updateUser(new User(1, "name"))).thenReturn(true);
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(200, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnNotFoundCodeGivenUpdateFails() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		//Mockito.when(mockUserRepository.updateUser(new User(1, "name"))).thenReturn(false);
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(404, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnServerErrorCodeGivenExceptionIsThrown() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		//Mockito.when(mockUserRepository.updateUser(new User(1, "name"))).thenThrow(new HibernateException("Update Failed"));
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(500, result.getHttpCode());
	}

}
