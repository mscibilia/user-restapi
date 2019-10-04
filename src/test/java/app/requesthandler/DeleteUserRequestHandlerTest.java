package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessResourceFailureException;

import app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserRequestHandlerTest {
	
	@Mock
	private UserRepository mockUserRepository;

	@Test
	public void processShouldReturnSuccessCodeGivenNoExpceptionIsThrown() {
		int idToDelete = 1;
		Mockito.doNothing().when(mockUserRepository).deleteById(idToDelete);
		
		DeleteUserRequestHandler sut = new DeleteUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(idToDelete));
		Answer result  = sut.process(new EmptyRequestPayload(), urlParams);
		
		assertEquals(200, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnNotFoundCodeGivenUserDeletionFailed() {
		int idToDelete = 1;
		Mockito.doThrow(DataAccessResourceFailureException.class).when(mockUserRepository).deleteById(idToDelete);
		DeleteUserRequestHandler sut = new DeleteUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(idToDelete));
		Answer result  = sut.process(new EmptyRequestPayload(), urlParams);
		
		assertEquals(500, result.getHttpCode());
	}

}
