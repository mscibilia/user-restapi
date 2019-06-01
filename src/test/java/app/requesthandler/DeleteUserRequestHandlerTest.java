package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import app.repository.UserRepository;

public class DeleteUserRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessCodeGivenUserDeletedSuccessfully() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		int idToDelete = 1;
		//Mockito.when(mockUserRepository.deleteUser(idToDelete)).thenReturn(true);
		
		DeleteUserRequestHandler sut = new DeleteUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(idToDelete));
		Answer result  = sut.process(new EmptyRequestPayload(), urlParams);
		
		assertEquals(200, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnNotFoundCodeGivenUserDeletionFailed() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		int idToDelete = 1;
		//Mockito.when(mockUserRepository.deleteUser(idToDelete)).thenReturn(false);
		
		DeleteUserRequestHandler sut = new DeleteUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(idToDelete));
		Answer result  = sut.process(new EmptyRequestPayload(), urlParams);
		
		assertEquals(404, result.getHttpCode());
	}

}
