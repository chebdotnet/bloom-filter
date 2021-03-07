package homework.boomfilter.hash;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class CustomHash {

    private final int seed = getRandomNumberUsingInts(1, 1000);

    private static int getRandomNumberUsingInts(int min, int max) {
        return new Random().ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public int hash(String value) {
        int hash = 1;
        for (int i = 0; i < value.length(); i++) {
            hash = (seed * hash + value.charAt(i));
        }
        log.debug("hash: {} for value {}", hash, value);
        return Math.abs(hash);
    }

}
