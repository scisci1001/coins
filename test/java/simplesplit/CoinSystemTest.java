package simplesplit;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinSystemTest {

    @Test
    public void testCoinSystem() {
        try {
            new CoinSystem(new int[]{1, 3, 45});
            new CoinSystem(new int[]{1, 7, 35, 78});
        } catch (InputParameterException e) {
            fail("Input parameter error");
        }
        assertThrows(InputParameterException.class, () -> new CoinSystem(new int[]{2, 3}), "Must be minimum 3 element");
        assertThrows(InputParameterException.class, () -> new CoinSystem(new int[]{2, 3, 6, 8, 9, 90, 98}), "Must be maximum 6 element");
        assertThrows(InputParameterException.class, () -> new CoinSystem(new int[]{1, 73, 45}), "Must be ordered");
        assertThrows(InputParameterException.class, () -> new CoinSystem(new int[]{1, 7, 35, 102}), "Must be less than 100");
    }

    @Test
    public void testGetCount() {
        CoinSystem actual = null;
        try {
            actual = new CoinSystem(new int[]{1, 3, 45});
            assertEquals(3, actual.getCount());

            actual = new CoinSystem(new int[]{1, 7, 35, 78});
            assertEquals(4, actual.getCount());
        } catch (InputParameterException e) {
            fail("Must not throw Exception");
        }
        final CoinSystem finalActual = actual;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> finalActual.getCoin(8));
    }

    @Test
    public void testGetElem() {
        CoinSystem actual = null;
        try {
            actual = new CoinSystem(new int[]{1, 3, 45});
            assertEquals(3, actual.getCount());
            assertEquals(3, actual.getCoin(1));
            assertEquals(45, actual.getCoin(2));

            actual = new CoinSystem(new int[]{1, 7, 35, 78});
            assertEquals(4, actual.getCount());
        } catch (InputParameterException e) {
            fail("Input parameter error");
        }
        final CoinSystem finalActual = actual;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> finalActual.getCoin(8));
    }

}