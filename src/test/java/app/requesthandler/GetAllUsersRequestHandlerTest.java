package app.requesthandler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import app.model.User;
import app.repository.UserRepository;

public class GetAllUsersRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessAndUserList() {
		UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
		List<User> userListInDb = new ArrayList<>();
		userListInDb.add(new User(1, "john"));
		userListInDb.add(new User(2, "jane"));
		
		//Mockito.when(mockUserRepository.getUserList()).thenReturn(userListInDb);
		
		GetAllUsersRequestHandler sut = new GetAllUsersRequestHandler(mockUserRepository);
		
		Map<String, String> urlParams = new HashMap<>();
		Answer result = sut.process(new EmptyRequestPayload(), urlParams);
		
		String expectedJsonString = new Gson().toJson(userListInDb);
		assertEquals(new Answer(200, expectedJsonString), result);
		
	}

}
