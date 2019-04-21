package pt.feup.cm.config;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import pt.feup.cm.auth.AuthStorage;
import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.exception.BusinessException;

public class AuthTokenGenerator {

	public static PublicKey publicKey;
	private static PrivateKey privateKey;

	private static final long validityInMilliseconds = 600000;

	public AuthTokenGenerator() {
	}

	public void generateKey(String username) {
		KeyPair kp = RsaProvider.generateKeyPair(1024);
		publicKey = kp.getPublic();
		privateKey = kp.getPrivate();
	}

	public String createToken(String username) {
		generateKey(username);
		Claims claims = Jwts.claims().setSubject(username);
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
		AuthStorage.instance().add(token, publicKey, privateKey);
		return token;
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(AuthStorage.instance().get(token)).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(String bearerToken) {
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) throws BusinessException {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				throw new BusinessException(ErrorCode.AUTH_TOKEN_EXPIRED);
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new BusinessException(e, ErrorCode.AUTH_TOKEN_INVALID);
		}
	}
}
