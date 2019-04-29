package pt.feup.cm.config;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import pt.feup.cm.warehouse.entities.DbCartItem;

public class PaymentGenerator {

	public static final int KEY_SIZE = 512;
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGN_ALGORITHM = "SHA256WithRSA";
	public static BigInteger PUBLIC_EXPONENT = new BigInteger("65537");

	public static String generateId() {
		return UUID.randomUUID().toString().toUpperCase() + "_PM_" + Calendar.getInstance().getTimeInMillis();
	}

	public static boolean validateToken(byte[] message, byte[] publicRsaKey, List<DbCartItem> cartItems) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			RSAPublicKeySpec RSAPub = new RSAPublicKeySpec(new BigInteger(publicRsaKey), PUBLIC_EXPONENT);
			PublicKey pubKey = keyFactory.generatePublic(RSAPub);

			int nr = message[0];
            byte[] mess = new byte[nr + 1];
            byte[] sign = new byte[KEY_SIZE / 8];
            ByteBuffer bb = ByteBuffer.wrap(message);
            bb.get(mess, 0, nr + 1);
            bb.get(sign, 0, KEY_SIZE / 8);
            
            Signature sg = Signature.getInstance(SIGN_ALGORITHM);
            sg.initVerify(pubKey);
            sg.update(mess);
            return sg.verify(sign) && activeCartValid(message, cartItems);
		} catch (GeneralSecurityException e) {
			return false;
		}
	}

	private static boolean activeCartValid(byte[] message, List<DbCartItem> cartItems) {
		List<Integer> products = new ArrayList<>();
		for (DbCartItem cartItem : cartItems) {
			products.add((int) cartItem.getProductId());
        }
		int nr = message[0];
		for (int k=1; k<=nr; k++) {
			if (products.contains(Integer.valueOf(message[k]))) {
				products.remove(Integer.valueOf(message[k]));
			} else {
				return false;
			}
        }
		if (!products.isEmpty()) {
			return false;
		}
		return true;
	}
}
