package pt.feup.cm.config;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentRandomizer {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentRandomizer.class.getName());
	
	private static Random r = new Random();
	public static final int SUCCESS_PAYMENT_PERCENTAGE = 95;
	public static final int MAX_PERCENTAGE = 100;

	public static boolean isSuccess() {
		int randomResult = r.nextInt(MAX_PERCENTAGE);
		logger.info("PaymentRandomizer generates {}%", r.nextInt(MAX_PERCENTAGE));
		return randomResult < SUCCESS_PAYMENT_PERCENTAGE;
	}
}
