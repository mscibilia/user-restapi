package app.requesthandler;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.junit.Test;
import org.mockito.Mockito;

import app.model.User;
import app.persistence.HibernateUserHelper;
import app.persistence.UserHelper;

public class UpdateUserRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessCodeGivenUpdateIsSuccessful() {
		UserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		Mockito.when(mockUserHelper.updateUser(new User(1, "name"))).thenReturn(true);
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserHelper);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(200, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnNotFoundCodeGivenUpdateFails() {
		UserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		Mockito.when(mockUserHelper.updateUser(new User(1, "name"))).thenReturn(false);
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserHelper);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(404, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnServerErrorCodeGivenExceptionIsThrown() {
		UserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		Mockito.when(mockUserHelper.updateUser(new User(1, "name"))).thenThrow(new HibernateException("Update Failed"));
		UpdateUserRequestHandler sut = new UpdateUserRequestHandler(mockUserHelper);
		
		IndividualUserOperationRequestPayload validRequestPayload = new IndividualUserOperationRequestPayload(1, "name");
		Map<String, String> urlParams = new HashMap<>();
		
		Answer result = sut.process(validRequestPayload, urlParams);
		
		assertEquals(500, result.getHttpCode());
	}

}
