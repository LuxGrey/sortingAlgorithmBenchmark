import java.util.Date;
import java.util.Random;
import orderable.OrderableProfile;

public class DataGenerator {
  // Random-instance with current time as seed
  private static final Random generator = new Random(new Date().getTime());
  private static final int strLeftLimit = 97; // letter 'a'
  private static final int strRightLimit = 122; // letter 'z'

  // generates an Array of OrderableProfile with random values
  // number of objects is determined by size
  // value-range of key is determined by keyLowerBound and keyUpperBound
  // the 0-th element within the list will automatically have the minimum key-value
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
