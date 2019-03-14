package pt.feup.cm.config;

import java.util.Random;

public class PaymentRandomizer {
	
	private static Random r = new Random();
	public static final int SUCCESS_PAYMENT_PERCENTAGE = 95;
	public static final int MAX_PERCENTAGE = 100;

	public static boolean isSuccess() {
		int randomResult = r.nextInt(MAX_PERCENTAGE);
		System.out.print("PaymentRandomizer generates " + r.nextInt(MAX_PERCENTAGE) + "%");
		return randomResult < SUCCESS_PAYMENT_PERCENTAGE;
	}
}
