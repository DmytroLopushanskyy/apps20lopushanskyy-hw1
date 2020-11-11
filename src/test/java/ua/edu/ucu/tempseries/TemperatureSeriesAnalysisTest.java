package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis seriesAnalysis;
    private TemperatureSeriesAnalysis emptyAnalysis;

    @Before
    public void init() {
        // setup input data
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};

        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        emptyAnalysis = new TemperatureSeriesAnalysis();
    }

    @Test
    public void testAverageWithOneElementArray() {
        // set expected result
        double expResult = 1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        // expect exception here
        emptyAnalysis.average();
    }

    @Test(expected = InputMismatchException.class)
    public void testInputMismatch() {
        double[] temperatureSeries = {3.0, -275.0, 1.0, 5.0};
        new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation() {
        double expResult = 3.7416;
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test
    public void testMax() {
        double expResult = 5.0;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double expResult = -5.0;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToZero() {
        double expResult = 1.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToValue() {
        double expResult = 5.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(4);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testLessThan() {
        double[] expResult = {-5, 1};
        double[] actualResult = seriesAnalysis.findTempsLessThen(2);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testGreaterThan() {
        double[] expResult = {3, 5};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(2);
        System.out.println(Arrays.toString(actualResult));
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};

        double expResult = 8.0;
        double actualResult = seriesAnalysis.addTemps(temperatureSeries);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();
        assertEquals(1.0, actualResult.getAvgTemp(), 0.00001);
        assertEquals(3.74165, actualResult.getDevTemp(), 0.00001);
        assertEquals(5.0, actualResult.getMaxTemp(), 0.00001);
        assertEquals(-5.0, actualResult.getMinTemp(), 0.00001);
    }

}
