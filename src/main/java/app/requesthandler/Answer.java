package app.requesthandler;

public class Answer {
	private final int httpCode;
	
	private final String responseBody;

	/**
	 * @return the httpCode
	 */
	public int getHttpCode() {
		return httpCode;
	}


	/**
	 * @param httpCode
	 * @param responseBody
	 */
	public Answer(int httpCode, String responseBody) {
		this.httpCode = httpCode;
		this.responseBody = responseBody;
	}


	/**
	 * @return the responseBody
	 */
	public String getResponseBody() {
		return responseBody;
	}


	@Override
	public String toString() {
		return "Answer [httpCode=" + httpCode + ", responseBody=" + responseBody + "]";
	}

}
