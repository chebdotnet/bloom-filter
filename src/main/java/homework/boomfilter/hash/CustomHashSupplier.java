package homework.boomfilter.hash;


import java.util.function.Supplier;

public class CustomHashSupplier implements Supplier<CustomHash> {

    @Override
    public CustomHash get() {
        return new CustomHash();
    }

}
