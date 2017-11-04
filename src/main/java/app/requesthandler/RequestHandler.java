package app.requesthandler;

import java.util.Map;

public interface RequestHandler<V extends Validable> {

	public Answer process(V validRequestPayload , Map<String, String> urlParams);
}
