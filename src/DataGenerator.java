import java.util.Date;
import java.util.Random;
import orderable.OrderableProfile;

public class DataGenerator {
  // Random-Instanz mit aktuellem Zeitpunkt als seed
  private static final Random generator = new Random(new Date().getTime());
  private static final int strLeftLimit = 97; // Buchstabe 'a'
  private static final int strRightLimit = 122; // Buchstabe 'z'

  // generiert ein Array von OrderableProfile mit zufälligen Werten
  // Anzahl an Objekten wird durch size bestimmt
  // Wertebereich von key wird durch keyLowerBound und keyUpperBound eingeschränkt
  // das nullte Element in der Liste hat automatisch den minimalen key-Wert
  public static OrderableProfile[] generateTestData(int size, int keyLowerBound, int keyUpperBound) {
    OrderableProfile[] testData = new OrderableProfile[size];

    testData[0] = (OrderableProfile) generateOrderableProfile(keyLowerBound, keyUpperBound).minKey();

    for(int i = 1; i < testData.length; i++) {
      testData[i] = generateOrderableProfile(keyLowerBound, keyUpperBound);
    }

    return testData;
  }

  private static OrderableProfile generateOrderableProfile(int keyLowerBound, int keyUpperBound) {
    return new OrderableProfile(
        generator.nextInt((keyUpperBound - keyLowerBound) + 1) + keyLowerBound,
        generateString(6),
        generateString(8),
        generateString(10),
        new Date()
    );
  }

  private static String generateString(int length) {
    return generator.ints(strLeftLimit, strRightLimit + 1)
        .limit(length)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
