package pt.feup.cm.entities.response;

public class TokenResponse extends BaseResponse {

	private String token;

	public TokenResponse() {
	}

	public TokenResponse(String token) {
		this.token = token;
	}

	public TokenResponse(int errorCode) {
		super(errorCode);
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
