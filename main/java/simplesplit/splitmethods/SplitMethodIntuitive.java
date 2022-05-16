package simplesplit.splitmethods;

import simplesplit.AllSplit;
import simplesplit.CoinSystem;
import simplesplit.IMinimum;

public class SplitMethodIntuitive extends SplitMethodAbstract {

    public SplitMethodIntuitive(IMinimum minimumFunction) {
        super(minimumFunction);
    }

    @Override
    public AllSplit generate(CoinSystem coinSystem) {
        AllSplit allSplit = init(coinSystem);
        int coinsCount = coinSystem.getCount();

        generate(coinsCount - 1, getMinimumFunction().getMaxValue(), allSplit, coinSystem);
        return allSplit;
    }

    private void generate(int coinIndex, int findTo, AllSplit allSplit, CoinSystem coinSystem) {
        if (coinIndex == 0) {
            for (int i = 0; i < findTo; i++) {
                allSplit.setSplit(i + 1, allSplit.getSplit(i).plusOneAt(coinIndex));
            }
        } else {
            int coinValue = coinSystem.getCoin(coinIndex);
            generate(coinIndex - 1, coinValue - 1, allSplit, coinSystem);
            for (int i = coinValue; i <= findTo; i++) {
                allSplit.setSplit(i, allSplit.getSplit(i - coinValue).plusOneAt(coinIndex));
            }
        }
    }

    @Override
    public String getName() {
        return "Intuitive";
    }

}
