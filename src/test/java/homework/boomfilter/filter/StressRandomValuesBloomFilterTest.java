package homework.boomfilter.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class StressRandomValuesBloomFilterTest {

    private static final int DATA_ITEMS_COUNT = 50000;

    private final BloomFilter bloomFilter = new BloomFilter();

    private final List<String> data = new ArrayList<>();

    @Before
    public void setUp() {
        IntStream.range(0, DATA_ITEMS_COUNT).forEach(a -> data.add(getGeneratedString(3, 10)));
        data.forEach(bloomFilter::add);
    }

    @Test
    public void shouldContainDataInFilter() {
        data.forEach(item -> assertTrue(bloomFilter.probablyContain(item)));
    }

    @Test
    public void definitelyNotContainDataInFilter() {
        final List<String> fakeData = new ArrayList<>();
        IntStream.range(0, DATA_ITEMS_COUNT).forEach(a -> fakeData.add(getGeneratedString(11, 22)));
        fakeData.forEach(item -> assertFalse(bloomFilter.probablyContain(item)));
    }

    private String getGeneratedString(int min, int max) {
        int length = getRandomNumberUsingInts(min, max);
        String generatedString = RandomStringUtils.random(length, true, false);
        log.debug("Generated string is {} ", generatedString);
        return generatedString;
    }

    private int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
