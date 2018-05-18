package app.requesthandler;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import app.persistence.HibernateUserHelper;
import app.persistence.UserHelper;

public class DeleteUserRequestHandlerTest {

	@Test
	public void processShouldReturnSuccessCodeGivenUserDeletedSuccessfully() {
		UserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		int idToDelete = 1;
		Mockito.when(mockUserHelper.deleteUser(idToDelete)).thenReturn(true);
		
		DeleteUserRequestHandler sut = new DeleteUserRequestHandler(mockUserHelper);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(idToDelete));
		Answer result  = sut.process(new EmptyRequestPayload(), urlParams);
		
		assertEquals(200, result.getHttpCode());
	}
	
	@Test
	public void processShouldReturnNotFoundCodeGivenUserDeletionFailed() {
		UserHelper mockUserHelper = Mockito.mock(HibernateUserHelper.class);
		int idToDelete = 1;
		Mockito.when(mockUserHelper.deleteUser(idToDelete)).thenReturn(false);
		
		DeleteUserRequestHandler sut = new DeleteUserRequestHandler(mockUserHelper);
		
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put(":id", String.valueOf(idToDelete));
		Answer result  = sut.process(new EmptyRequestPayload(), urlParams);
		
		assertEquals(404, result.getHttpCode());
	}

}
