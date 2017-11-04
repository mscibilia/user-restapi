package app.requesthandler;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
			return new Answer(HTTP_BAD_REQUEST, null);
		}
	}

	abstract Answer processImpl(V validRequestPayload, Map<String, String> urlParams);

	@Override
	public Object handle(Request request, Response response) throws Exception {
		V requestPayload = gson.fromJson(request.body(), TypeToken.get(requestPayloadClass).getType());
		process(requestPayload, request.params());
		
		return null;
	}
	
}
