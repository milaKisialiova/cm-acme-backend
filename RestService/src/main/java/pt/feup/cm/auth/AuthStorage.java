package pt.feup.cm.auth;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class AuthStorage {

	private static Map<String, AuthData> authStorage;
	private static AuthStorage instance;

	private AuthStorage() {
		authStorage = new HashMap<String, AuthData>();
	}

	public static AuthStorage instance() {
		if (instance == null) {
			instance = new AuthStorage();
		}
		return instance;
	}
	
	public void add(String token, PublicKey publicKey, PrivateKey privateKey) {
		authStorage.put(token, new AuthData(publicKey, privateKey));
	}

	public PublicKey get(String token) {
		return authStorage.get(token).getPublicKey();
	}
}
