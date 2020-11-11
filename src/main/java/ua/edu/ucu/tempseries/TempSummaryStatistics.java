package ua.edu.ucu.tempseries;


public class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double maxTemp;
    private double minTemp;

    public TempSummaryStatistics(double avg, double dev,
                                 double max, double min) {
        avgTemp = avg;
        devTemp = dev;
        maxTemp = max;
        minTemp = min;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }
}
