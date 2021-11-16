package image;

import org.junit.Test;
import static org.junit.Assert.*;

/** Testing methods in MatrixUtils.java.
 *  @author Matthew Sahim
 */

public class MatrixUtilsTest {
    double[][] test = new double[][] {{10, 10, 10, 10},
                                      {10,  7,  3, 10},
                                      {10,  3, 10, 10},
                                      {10,  2,  3, 10},
                                      {10,  7, 10, 10}};
    double[][] result1 = new double[][] {{10, 10, 10, 10},
                                         {20, 17, 13, 20},
                                         {27, 16, 23, 23},
                                         {26, 18, 19, 33},
                                         {28, 25, 28, 29}};
    double[][] result2 = new double[][] {{10, 20, 27, 26},
                                         {10, 17, 16, 26},
                                         {10, 13, 22, 25},
                                         {10, 12, 15, 25},
                                         {10, 17, 22, 25}};

    @Test
    public void accumulateVerticalTest() {
        assertArrayEquals(result1, MatrixUtils.accumulateVertical(test));
    }
    @Test
    public void accumulateTest() {
        assertArrayEquals(result2,
                MatrixUtils.accumulate(test, MatrixUtils.Orientation.HORIZONTAL));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
