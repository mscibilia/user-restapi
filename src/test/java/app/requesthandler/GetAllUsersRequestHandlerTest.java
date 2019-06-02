package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.gson.Gson;

import app.model.User;
import app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class GetAllUsersRequestHandlerTest {
	
	@Mock
	private UserRepository mockUserRepository;

	@Test
	public void processShouldReturnSuccessAndUserList() {
		List<User> userListInDb = new ArrayList<>();
		userListInDb.add(new User(1, "john"));
		userListInDb.add(new User(2, "jane"));
		
		Mockito.when(mockUserRepository.findAll()).thenReturn(userListInDb);
		
		GetAllUsersRequestHandler sut = new GetAllUsersRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		Answer result = sut.process(new EmptyRequestPayload(), urlParams);
		
		String expectedJsonString = new Gson().toJson(userListInDb);
		assertEquals(new Answer(200, expectedJsonString), result);
		
	}

}
