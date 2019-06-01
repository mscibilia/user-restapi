package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import app.model.User;
import app.repository.UserRepository;

public class GetUserRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessCodeAndUserGivenRequestForUserThatExists() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		int userId = 1;
		User userInDb = new User(userId, "test");
		//Mockito.when(mockUserRepository.getUserById(userId)).thenReturn(userInDb);
		GetUserRequestHandler sut = new GetUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(userId));
		Answer result = sut.process(new EmptyRequestPayload(), urlParams );
		
		String expectedJsonString = new Gson().toJson(userInDb);
		
		assertEquals(new Answer(200, expectedJsonString), result);
	}
	
	@Test
	public void processShouldReturnNotFoundCodeGivenRequestForUserThatDoesNotExists() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		int userId = 1;
		int otherUserId = 2;
		User userInDb = new User(userId, "test");
		//Mockito.when(mockUserRepository.getUserById(userId)).thenReturn(userInDb);
		GetUserRequestHandler sut = new GetUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(otherUserId));
		Answer result = sut.process(new EmptyRequestPayload(), urlParams );
		
		
		assertEquals(404, result.getHttpCode());
	}

}
