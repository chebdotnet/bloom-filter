package homework.boomfilter.filter;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readString;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterForStringsTest {

    private final static byte HASHES_COUNT = 127;
    private final BloomFilter<String> bloomFilter = new BloomFilter<>(HASHES_COUNT);

    @Before
    @SneakyThrows
    public void setUp() {
        List<String> data = asList(readString(Path.of("src/test/resources/cities.txt")).split(","));
        bloomFilter.addAll(data);
    }

    @Test
    public void probablyContainCitiesInFilter() {
        assertTrue(bloomFilter.probablyContain("Riga"));
        assertTrue(bloomFilter.probablyContain("Madrid"));
        assertTrue(bloomFilter.probablyContain("Tallinn"));
    }

    @Test
    public void definitelyNotContainCitiesInFilter() {
        assertFalse(bloomFilter.probablyContain("BMW"));
        assertFalse(bloomFilter.probablyContain("Riiga"));
        assertFalse(bloomFilter.probablyContain("Lisboa"));
    }
}
