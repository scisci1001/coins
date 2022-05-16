package simplesplit.splitmethods;

import simplesplit.AllSplit;
import simplesplit.CoinSystem;
import simplesplit.IMinimum;
import simplesplit.Split;

public class SplitMethodPlusMinus extends SplitMethodAbstract {

    private int coinsCount;
    private Split split;
    private CoinSystem coinSystem;
    private AllSplit allSplit;
    private int numReady;

    public SplitMethodPlusMinus(IMinimum minimumFunction) {
        super(minimumFunction);
    }

    @Override
    public AllSplit generate(CoinSystem coinSystem) {
        allSplit = init(coinSystem);
        coinsCount = coinSystem.getCount();
        this.coinSystem = coinSystem;
        if (getMCD() > 1) {
            Split splitTemp = new Split(coinsCount);
            for (int i = 0; i < coinsCount; i++) {
                splitTemp.setValueAt(i, 10);
            }
            for (int i = 0; i <= getMinimumFunction().getMaxValue(); i++) {
                allSplit.setSplit(i, new Split(splitTemp));
            }
            return allSplit;
        }
        numReady = 0;

        split = new Split(coinsCount);
        for (int i = 1; ; i++) {
            deal(0, i);
            if (numReady == getMinimumFunction().getMaxValue()) {
                return allSplit;
            }
        }
    }

    /**
     * getMCD
     *
     * @return Maximum Common Divisor of coinSystem
     */
    private int getMCD() {
        int min = coinSystem.getCoin(0);
        for (int i = 1; min > 1 && i < coinSystem.getCount(); i++) {
            min = getMCD(min, coinSystem.getCoin(i));
        }
        return min;
    }

    private int getMCD(int a, int b) {
        if (a == 1) {
            return a;
        }
        int c = b % a;
        if (c == 0) {
            return a;
        }
        return getMCD(c, a);
    }

    private void deal(int index, int num) {
        if (index == coinsCount - 1) {
            split.setValueAt(index, num);
            plusMinusVariants(new Split(split));
        } else {
            for (int i = num; i >= 0; i--) {
                split.setValueAt(index, i);
                deal(index + 1, num - i);
            }
        }
    }

    private void plusMinusVariants(Split splitPlusMinus) {
        while (splitPlusMinus.hasPositiveNumber()) {
            int sum = getPlusMinusSum(splitPlusMinus);
            if (sum > 0 && sum <= getMinimumFunction().getMaxValue() && allSplit.getSplit(sum) == null) {
                allSplit.setSplit(sum, new Split(splitPlusMinus));
                if (++numReady == getMinimumFunction().getMaxValue()) {
                    break;
                }
            }

            for (int i = 0; i < coinsCount; i++) {
                int num = splitPlusMinus.getValue(i);
                splitPlusMinus.setValueAt(i, -num);
                if (num > 0) {
                    break;
                }
            }
        }
    }

    private int getPlusMinusSum(Split splitPlusMinus) {
        int sum = 0;
        for (int i = 0; i < coinsCount; i++) {
            sum += splitPlusMinus.getValue(i) * coinSystem.getCoin(i);
        }
        return sum;
    }

    @Override
    public int getStartNum() {
        return 0;
    }


    @Override
    public String getName() {
        return "Plus-Minus";
    }
}
