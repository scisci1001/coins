package simplesplit.splitmethods;

import simplesplit.AllSplit;
import simplesplit.CoinSystem;
import simplesplit.IMinimum;
import simplesplit.Split;

public abstract class SplitMethodAbstract {

    public static final int START_NUM = 1;
    private final IMinimum minimumFunction;

    protected SplitMethodAbstract(IMinimum minimumFunction) {
        this.minimumFunction = minimumFunction;
    }

    protected AllSplit init(CoinSystem coinSystem) {
        AllSplit allSplit = new AllSplit(getMinimumFunction().getMaxValue());
        allSplit.setSplit(0, new Split(coinSystem.getCount()));
        return allSplit;
    }

    public IMinimum getMinimumFunction() {
        return minimumFunction;
    }

    public int getStartNum() {
        return START_NUM;
    }

    public abstract AllSplit generate(CoinSystem coinSystem);

    public abstract String getName();

}
