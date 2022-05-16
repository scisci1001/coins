package simplesplit.splitmethods;

import simplesplit.AllSplit;
import simplesplit.CoinSystem;
import simplesplit.IMinimum;
import simplesplit.Split;

public class SplitMethodNonIntuitive extends SplitMethodAbstract {

    public SplitMethodNonIntuitive(IMinimum minimumFunction) {
        super(minimumFunction);
    }

    @Override
    public AllSplit generate(CoinSystem coinSystem) {
        AllSplit allSplit = init(coinSystem);
        int coinsCount = coinSystem.getCount();

        for (int i = 1; i < allSplit.getLength(); i++) {
            Split minSplit = new Split(coinsCount);
            minSplit.setValueAt(0, getMinimumFunction().getMaxValue() + 1);
            for (int j = 0; j < coinsCount; j++) {
                int coinValue = coinSystem.getCoin(j);
                if (i - coinValue >= 0) {
                    Split newSplit = allSplit.getSplit(i - coinValue).plusOneAt(j);
                    if (newSplit.countOfCoins() < minSplit.countOfCoins()) {
                        minSplit = newSplit;
                    }
                }
            }
            allSplit.setSplit(i, minSplit);
        }
        return allSplit;
    }

    @Override
    public String getName() {
        return "Nonintuitive";
    }

}
