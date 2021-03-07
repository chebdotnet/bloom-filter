package homework.boomfilter.filter;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;

import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readString;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterForNumbersTest {

    private final static byte HASHES_COUNT = 127;
    private final BloomFilter<Integer> bloomFilter = new BloomFilter<>(HASHES_COUNT);

    @Before
    @SneakyThrows
    public void setUp() {
        asList(readString(Path.of("src/test/resources/numbers.txt")).split(","))
                .forEach(item -> bloomFilter.add(parseInt(item.trim())));
    }

    @Test
    public void probablyContainNumbersInFilter() {
        assertTrue(bloomFilter.probablyContain(268837));
        assertTrue(bloomFilter.probablyContain(680094));
        assertTrue(bloomFilter.probablyContain(415580));
    }

    @Test
    public void definitelyNotContainNumbersInFilter() {
        assertFalse(bloomFilter.probablyContain(352354));
        assertFalse(bloomFilter.probablyContain(254));
        assertFalse(bloomFilter.probablyContain(254234));
    }

}
