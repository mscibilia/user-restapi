package app.requesthandler;

public class EmptyRequestPayload implements Validable {

	@Override
	public boolean isValid() {
		return true;
	}
}
