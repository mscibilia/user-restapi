package app.requesthandler;

import app.Validable;

public class EmptyRequestPayload implements Validable {

	@Override
	public boolean isValid() {
		return true;
	}
}
