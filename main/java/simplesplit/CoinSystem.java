package simplesplit;

import java.util.Arrays;

public class CoinSystem {
    private int[] coins;

    public CoinSystem(int[] coins) throws InputParameterException {
        this.coins = coins;
        if (coins.length < Const.COINS_COUNT_MIN || coins.length > Const.COINS_COUNT_MAX) {
            throw new InputParameterException("Coins length must be between " + Const.COINS_COUNT_MIN + " and " + Const.COINS_COUNT_MAX);
        }
        for (int i = 1; i < coins.length; i++) {
            if (coins[i - 1] >= coins[i]) {
                throw new InputParameterException("Must be sorted");
            }
        }
        if (coins[coins.length - 1] > Const.COIN_MAX_VALUE) {
            throw new InputParameterException("Must be less or equals, then " + Const.COIN_MAX_VALUE);
        }
    }

    public int getCount() {
        return coins.length;
    }

    public void multiplyBy(int multiply) {
        coins = Arrays.stream(coins).map(i -> i * multiply).toArray();
    }

    public int getCoin(int i) {
        return coins[i];
    }

    @Override
    public String toString() {
        return Arrays.toString(coins);
    }

}
