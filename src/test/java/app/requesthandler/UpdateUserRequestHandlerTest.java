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

import app.model.User;
import app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserRequestHandlerTest {
	
	@Mock
	private UserRepository mockUserRepository;

	@Test
	public void processShouldReturnSuccessCodeGivenUpdateIsSuccessful() {
		Mockito.when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(new User());
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(200, result.getHttpCode());
	}
	
	
	@Test
	public void processShouldReturnServerErrorCodeGivenExceptionIsThrown() {
		Mockito.when(mockUserRepository.save(Mockito.any(User.class))).thenThrow(new DataAccessResourceFailureException("Failed"));
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(500, result.getHttpCode());
	}

}
