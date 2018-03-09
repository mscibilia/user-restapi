package app.requesthandler;

public class Answer {
	private final int httpCode;
	
	private final String responseBody;
	
	private final boolean shouldReturnJson;

	/**
	 * @param httpCode
	 * @param responseBody
	 * @param shouldReturnJson
	 */
	public Answer(int httpCode, String responseBody, boolean shouldReturnJson) {
		this.httpCode = httpCode;
		this.responseBody = responseBody;
		this.shouldReturnJson = shouldReturnJson;
	}


	/**
	 * @return the httpCode
	 */
	public int getHttpCode() {
		return httpCode;
	}


	/**
	 * @return the responseBody
	 */
	public String getResponseBody() {
		return responseBody;
	}


	/**
	 * @return the shouldReturnJson
	 */
	public boolean getShouldReturnJson() {
		return shouldReturnJson;
	}


	@Override
	public String toString() {
		return "Answer [httpCode=" + httpCode + ", responseBody=" + responseBody + ", shouldReturnJson="
				+ shouldReturnJson + "]";
	}

}
