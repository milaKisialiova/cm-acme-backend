package pt.feup.cm.config;

import java.util.Calendar;
import java.util.UUID;

public class TokenGenerator {

	public static String generate() {
		return UUID.randomUUID().toString().toUpperCase() + "_PM_" + Calendar.getInstance().getTimeInMillis();
	}
}
