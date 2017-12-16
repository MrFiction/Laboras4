/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.time.LocalDate;
import java.util.Random;


/**
 *
 * @author Dainius
 */
public class ValueGenerator {

    static LocalDate randomDate() {
        Random dateGen = new Random();
        int minDay = (int) LocalDate.of(1980, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2017, 1, 1).toEpochDay();
        long randomDay = minDay + dateGen.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

    static String generateString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random stringGen = new Random();
        String randomString = new String();
        while (randomString.length() < 15) {
            randomString += chars.charAt(stringGen.nextInt(36));
        }
        return randomString;
    }

    static double randomPrice() {
        Random priceGen = new Random();
        double randomPrice;
        randomPrice = priceGen.nextDouble() * 100;
        return randomPrice;

    }

    static int randomSerialNumber() {
        Random serialNumbGen = new Random();
        int randomSNumber;
        randomSNumber = serialNumbGen.nextInt(10000);
        return randomSNumber;

    }
       public static Kazkas[] generate(int sampleSize) {
          Kazkas[] set = new Kazkas[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            Kazkas obj = randomObject();
            set[i] =obj;
        }
        return set;
      }
       public static Kazkas randomObject() {
        return new Kazkas(ValueGenerator.randomPrice(), ValueGenerator.randomDate(), ValueGenerator.generateString(), ValueGenerator.randomSerialNumber());
    }
      public static int[] generateInt(int sampleSize) {
          Random intGen = new Random();
          int[] set = new int[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            
            set[i] = intGen.nextInt(sampleSize);
        }
        return set;
      }
}
