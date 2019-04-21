package pt.feup.cm.auth;

import java.security.PrivateKey;
import java.security.PublicKey;

public class AuthData {
	
	public PublicKey publicKey;
	private PrivateKey privateKey;
	
	public AuthData(PublicKey publicKey, PrivateKey privateKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	

}
