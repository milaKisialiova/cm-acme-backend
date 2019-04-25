package pt.feup.cm.config;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class PaymentGenerator {

	public static String generateId() {
		return UUID.randomUUID().toString().toUpperCase() + "_PM_" + Calendar.getInstance().getTimeInMillis();
	}
	
	public static boolean validateToken(String token, byte[] publicRsaKey) {
		try {
			X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(publicRsaKey);
		    KeyFactory kf = KeyFactory.getInstance("RSA");
		    PublicKey publicKey = kf.generatePublic(X509publicKey);
			Jws<Claims> claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
			if (claims.getBody().getSubject() != null
					&& claims.getBody().getSubject().equals("Payment")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
