package homework.boomfilter.filter;

import com.google.common.base.Preconditions;
import com.netflix.hollow.core.memory.ThreadSafeBitSet;
import homework.boomfilter.hash.CustomHash;
import homework.boomfilter.hash.CustomHashSupplier;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.rangeClosed;


@Slf4j
public class BloomFilter<T> {

    private final static byte HASHES_COUNT_DEFAULT = 4;

    private final ThreadSafeBitSet bitSet;

    private final List<CustomHash> hashes = new ArrayList<>();

    public BloomFilter() {
        this(HASHES_COUNT_DEFAULT);
    }

    public BloomFilter(byte hashesCount) {
        Preconditions.checkArgument(hashesCount > 0);
        this.bitSet = new ThreadSafeBitSet();
        CustomHashSupplier customHashSupplier = new CustomHashSupplier();
        rangeClosed(0, hashesCount).forEach(item -> hashes.add(customHashSupplier.get()));
    }

    public void add(T value) {
        hashes.forEach(hash -> bitSet.set(hash.hash(value.toString())));
    }

    public void addAll(List<T> values) {
        values.forEach(this::add);
    }

    public boolean probablyContain(T value) {
        return hashes.stream().allMatch(hash -> bitSet.get(hash.hash(value.toString())));
    }

}
