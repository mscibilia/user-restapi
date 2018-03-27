package app.requesthandler;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.RequestHandler;
import app.Validable;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractRequestHandler<V extends Validable> implements RequestHandler<V>, Route {
	
	
	private static final int HTTP_BAD_REQUEST = 400;
	
	final Gson gson = new Gson();

	private Class<V> requestPayloadClass;
	
	public AbstractRequestHandler(Class<V> requestPayloadClass) {
		this.requestPayloadClass = requestPayloadClass;
	}

	@Override
	public Answer process(V validRequestPayload, Map<String, String> urlParams) {
		if(validRequestPayload.isValid())	{
			return processImpl(validRequestPayload, urlParams);
		}	else	{
			return new Answer(HTTP_BAD_REQUEST);
		}
	}

	abstract Answer processImpl(V validRequestPayload, Map<String, String> urlParams);

	@SuppressWarnings("unchecked")
	@Override
	public Object handle(Request request, Response response) throws Exception {
		Answer answer = null;
		V requestPayload;
		
		if(EmptyRequestPayload.class.getName().equals(requestPayloadClass.getName()))	{
			requestPayload = (V) new EmptyRequestPayload();
		}	else	{
			requestPayload = gson.fromJson(request.body(), TypeToken.get(requestPayloadClass).getType());
		}
		
		
		answer = process(requestPayload, request.params());
			
		if(shouldReturnJson(request))	{
			response.type("application/json");
		}
		
		response.status(answer.getHttpCode());
		response.body(answer.getResponseBody());
		
		return response.body();
	}

	private boolean shouldReturnJson(Request request) {
		String acceptHeader = request.headers("Accept");
		return acceptHeader != null && acceptHeader.contains("application/json");
	}
	
}
