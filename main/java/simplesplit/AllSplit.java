package simplesplit;

import java.util.Arrays;

public class AllSplit {
    private final Split[] splits;

    public AllSplit(int length) {
        splits = new Split[length + 1];
    }

    public int getLength() {
        return splits.length;
    }

    public Split getSplit(int index) {
        return splits[index];
    }

    public void setSplit(int index, Split split) {
        splits[index] = split;
    }

    public int getSum() {
        return Arrays.stream(splits).mapToInt(Split::countOfCoins).sum();

    }

}
