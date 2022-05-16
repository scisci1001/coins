package simplesplit;

import simplesplit.splitmethods.SplitMethodAbstract;

public class IterateAll {

    private final int[] coins;
    private final SplitMethodAbstract splitMethod;

    public IterateAll(int coinsNumber, SplitMethodAbstract splitMethod) {
        this.coins = new int[coinsNumber];
        this.splitMethod = splitMethod;
        for (int i = 0; i < coinsNumber; i++) {
            coins[i] = i + 1;
        }
    }

    public BestCoinSystems iterateAll() throws InputParameterException {
        BestCoinSystems bestCoinSystem = new BestCoinSystems(splitMethod.generate(new CoinSystem(coins)).getSum(), new CoinSystem(coins.clone()));
        while (next()) {
            bestCoinSystem.setSumCoinSystem(
                    splitMethod.generate(new CoinSystem(coins)).getSum(),
                    splitMethod.getMinimumFunction().format(new CoinSystem(coins.clone())));
        }
        return bestCoinSystem;
    }

    private boolean next() {
        return increment(coins.length - 1);
    }

    private boolean increment(int index) {
        if (index < splitMethod.getStartNum()) {
            return false;
        }
        int nextValue = ++coins[index];
        int max = splitMethod.getMinimumFunction().getMaxValue() + index - coins.length + 1;
        if (nextValue > max) {
            return increment(index - 1);
        }
        for (int i = index + 1; i < coins.length; i++) {
            coins[i] = ++nextValue;
        }
        return true;
    }

}
