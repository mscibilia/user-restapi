package app.requesthandler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessResourceFailureException;

import app.model.User;
import app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserRequestHandlerTest {
	
	@Mock
	private UserRepository mockUserRepository;

	@Test
	public void processShouldReturnSuccessCodeGivenUserCreationIsSuccessful() throws Exception {
		Mockito.when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(new User());
		
		CreateUserRequestHandler sut = new CreateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload individualUserOperationRequestPayload = new IndividualUserOperationRequestPayload(1, "test");
		Answer result = sut.process(individualUserOperationRequestPayload , null);
		
		assertEquals(201, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnErrorCodeGivenUserCreationFails() throws Exception {
		Mockito.doThrow(DataAccessResourceFailureException.class).when(mockUserRepository).save(Mockito.any(User.class));
		
		CreateUserRequestHandler sut = new CreateUserRequestHandler(mockUserRepository);
		
		IndividualUserOperationRequestPayload individualUserOperationRequestPayload = new IndividualUserOperationRequestPayload(1, "test");
		Answer result = sut.process(individualUserOperationRequestPayload , null);
		
		assertEquals(500, result.getHttpCode());
	}

}
