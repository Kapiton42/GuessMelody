package guess_melody.oauth;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Util {
  private static SecureRandom random = new SecureRandom();

  public static String getRandomString() {
    return new BigInteger(130, random).toString(32);
  }
}
