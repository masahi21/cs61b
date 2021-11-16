import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.

        assertEquals(0, 0); */
        assertEquals(3, CompoundInterest.numYears(2023));
        assertEquals(0, CompoundInterest.numYears(2020));
    }

    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        assertEquals(12.544, CompoundInterest.futureValue(10, 12, 2022), tolerance);
    }


    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
        assertEquals(295712.29, CompoundInterest.futureValueReal(1000000, 0, 2060, 3), tolerance);
        assertEquals(11.8026496, CompoundInterest.futureValueReal(10, 12, 2022, 3), tolerance);
    }

    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000, 2022, 10), tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        //System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
