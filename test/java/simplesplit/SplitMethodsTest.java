package simplesplit;

import org.junit.Test;
import simplesplit.splitmethods.SplitMethodNonIntuitive;
import simplesplit.splitmethods.SplitMethodPlusMinus;

import static org.junit.Assert.assertEquals;

public class SplitMethodsTest {

    private AllSplit underTest;

    @Test
    public void testSplitMethodNonIntuitive() throws InputParameterException {
        CoinSystem coinSystem = new CoinSystem(new int[]{1, 2, 5, 10, 20, 50});
        SplitMethodNonIntuitive nonIntuitive = new SplitMethodNonIntuitive(new MinimumOne());
        underTest = nonIntuitive.generate(coinSystem);
        Split split = underTest.getSplit(9);
        assertEquals(0, split.getValue(0));
        assertEquals(2, split.getValue(1));
        assertEquals(1, split.getValue(2));
        assertEquals(0, split.getValue(3));
        assertEquals(0, split.getValue(4));
        assertEquals(0, split.getValue(5));
        split = underTest.getSplit(69);
        assertEquals(0, split.getValue(0));
        assertEquals(2, split.getValue(1));
        assertEquals(1, split.getValue(2));
        assertEquals(1, split.getValue(3));
        assertEquals(0, split.getValue(4));
        assertEquals(1, split.getValue(5));
        split = underTest.getSplit(98);
        assertEquals(1, split.getValue(0));
        assertEquals(1, split.getValue(1));
        assertEquals(1, split.getValue(2));
        assertEquals(0, split.getValue(3));
        assertEquals(2, split.getValue(4));
        assertEquals(1, split.getValue(5));
    }

    @Test
    public void testSplitMethodPlusMinus() {
        try {
            SplitMethodPlusMinus splitMethodPlusMinus = new SplitMethodPlusMinus(new MinimumOne());
            underTest = splitMethodPlusMinus.generate(new CoinSystem(new int[]{6, 10, 15}));
            assertEquals(475, underTest.getSum());
            assertEquals(99, splitMethodPlusMinus.getMinimumFunction().getMaxValue());
        } catch (InputParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSplitMethodPlusMinus246() {
        try {
            SplitMethodPlusMinus splitMethodPlusMinus = new SplitMethodPlusMinus(new MinimumOne());
            underTest = splitMethodPlusMinus.generate(new CoinSystem(new int[]{2, 4, 6}));
            assertEquals(3000, underTest.getSum());
            assertEquals(99, splitMethodPlusMinus.getMinimumFunction().getMaxValue());
        } catch (InputParameterException e) {
            e.printStackTrace();
        }
    }

}