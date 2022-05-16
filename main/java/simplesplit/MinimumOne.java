package simplesplit;

public class MinimumOne implements IMinimum {

    @Override
    public int getMaxValue() {
        return Const.COIN_MAX_VALUE;
    }

    @Override
    public CoinSystem format(CoinSystem coinSystem) {
        return coinSystem;
    }

    @Override
    public String getName() {
        return " - Minimum 1";
    }

}
