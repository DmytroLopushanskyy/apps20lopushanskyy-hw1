package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    double avgTemp;
    double devTemp;
    double maxTemp;
    double minTemp;

    public TempSummaryStatistics(double avg, double dev, double max, double min) {
        avgTemp = avg;
        devTemp = dev;
        maxTemp = max;
        minTemp = min;
    }
}
