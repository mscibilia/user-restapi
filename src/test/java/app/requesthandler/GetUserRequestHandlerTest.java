package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.gson.Gson;

import app.model.User;
import app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class GetUserRequestHandlerTest {

	@Mock
	private UserRepository mockUserRepository;
	
	@Test
	public void processShouldReturnSuccessCodeAndUserGivenRequestForUserThatExists() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		int userId = 1;
		User userInDb = new User(userId, "test");
		Mockito.when(mockUserRepository.findById(userId)).thenReturn(Optional.of(userInDb));
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
		GetUserRequestHandler sut = new GetUserRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(userId));
		Answer result = sut.process(new EmptyRequestPayload(), urlParams );
		
		
		assertEquals(404, result.getHttpCode());
	}

}
