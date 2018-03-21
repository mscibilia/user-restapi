package app.requesthandler;

public class Answer {
	private final int httpCode;
	
	private final String responseBody;
	

	/**
	 * @param httpCode
	 * @param responseBody
	 */
	public Answer(int httpCode, String responseBody) {
		this.httpCode = httpCode;
		this.responseBody = responseBody;
	}
	
	public Answer(int httpCode) {
		this.httpCode = httpCode;
		this.responseBody = null;
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



	@Override
	public String toString() {
		return "Answer [httpCode=" + httpCode + ", responseBody=" + responseBody + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (httpCode != other.httpCode)
			return false;
		if (responseBody == null) {
			if (other.responseBody != null)
				return false;
		} else if (!responseBody.equals(other.responseBody))
			return false;
		return true;
	}


}
