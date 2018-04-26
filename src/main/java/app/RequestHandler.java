package app;

import java.util.Map;

import app.requesthandler.Answer;

public interface RequestHandler<V extends Validable> {

	public Answer process(V validRequestPayload , Map<String, String> urlParams);
}
