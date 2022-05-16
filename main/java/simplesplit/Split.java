package simplesplit;

import java.util.Arrays;

public class Split {

    private final int[] counts;

    public Split(int length) {
        counts = new int[length];
    }

    public Split(Split split) {
        counts = Arrays.copyOf(split.counts, split.counts.length);
    }

    public int getValue(int index) {
        return counts[index];
    }

    public int countOfCoins() {
        return Arrays.stream(counts).map(Math::abs).sum();
    }

    public void setValueAt(int index, int value) {
        counts[index] = value;
    }

    public Split plusOneAt(int index) {
        Split incremented = new Split(this);
        incremented.counts[index]++;
        return incremented;
    }

    public boolean hasPositiveNumber() {
        return Arrays.stream(counts).anyMatch(n -> n > 0);
    }

    @Override
    public String toString() {
        return countOfCoins() + " -- " + Arrays.toString(counts);

    }

}
