package simplesplit;

import simplesplit.splitmethods.SplitMethodAbstract;
import simplesplit.splitmethods.SplitMethodIntuitive;
import simplesplit.splitmethods.SplitMethodNonIntuitive;
import simplesplit.splitmethods.SplitMethodPlusMinus;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static Logger logger;

    static {
        InputStream stream = Main.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            logger = Logger.getLogger(Main.class.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        try {
            CoinSystem coinSystem = new CoinSystem(new int[]{1, 2, 5, 10, 20, 50});
            SplitMethodIntuitive splitMethodIntuitive = new SplitMethodIntuitive(new MinimumOne());
            logger.log(Level.INFO, "Euro best: {0}",
                    ((double) splitMethodIntuitive.generate(coinSystem).getSum()) / splitMethodIntuitive.getMinimumFunction().getMaxValue());

            SplitMethodAbstract[] splitMethods = new SplitMethodAbstract[]{
                    new SplitMethodIntuitive(new MinimumOne()),
                    new SplitMethodNonIntuitive(new MinimumOne()),
                    new SplitMethodIntuitive(new MinimumFive()),
                    new SplitMethodNonIntuitive(new MinimumFive()),
                    new SplitMethodPlusMinus(new MinimumOne())};

            for (int i = Const.COINS_COUNT_MIN; i <= Const.COINS_COUNT_MAX; i++) {
                for (SplitMethodAbstract splitMethod : splitMethods) {
                    BestCoinSystems bestCoinSystems = new IterateAll(i, splitMethod).iterateAll();
                    logger.log(Level.INFO, "{0} coins, method: {1}{2}, best average: {3}", new Object[]{
                            i,
                            splitMethod.getName(),
                            splitMethod.getMinimumFunction().getName(),
                            (((double) bestCoinSystems.getSum()) / splitMethod.getMinimumFunction().getMaxValue())});
                    bestCoinSystems.getCoinSystems().forEach(coinSystem1 ->
                            logger.log(Level.INFO, coinSystem1.toString()));
                }
            }
        } catch (InputParameterException e) {
            e.printStackTrace();
        }
    }

}
