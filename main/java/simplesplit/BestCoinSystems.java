package simplesplit;

import java.util.ArrayList;
import java.util.List;

public class BestCoinSystems {

    private final List<CoinSystem> coinSystems;
    private int sum;

    public BestCoinSystems(int sum, CoinSystem coinSystem) {
        this.sum = sum;
        coinSystems = new ArrayList<>();
        coinSystems.add(coinSystem);
    }

    public List<CoinSystem> getCoinSystems() {
        return coinSystems;
    }

    public int getSum() {
        return sum;
    }

    public void setSumCoinSystem(int sum, CoinSystem coinSystem) {
        if (this.sum < sum) {
            return;
        }
        if (this.sum > sum) {
            this.sum = sum;
            coinSystems.clear();
        }
        coinSystems.add(coinSystem);
    }
}
