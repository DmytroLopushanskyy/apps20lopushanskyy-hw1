package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double MIN_VALUE = -273;
    private static final int INITIAL_SIZE = 10;
    private double[] temperatureArray;
    private int arraySize;

    public TemperatureSeriesAnalysis() {
        temperatureArray = new double[INITIAL_SIZE];
        arraySize = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        // Check the input. All values should be >= -273.
        for (double temperature: temperatureSeries) {
            if (temperature < MIN_VALUE) {
                throw new InputMismatchException();
            }
        }
        temperatureArray = Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
        arraySize = temperatureSeries.length;
    }

    public double average() {
        checkIfNotEmpty();

        // Calculate the average by iterating over all temperatures.
        double average = 0;
        for (double temperature: temperatureArray) {
            average += temperature;
        }
        return average / arraySize;
    }

    public double deviation() {
        checkIfNotEmpty();

        // Calculate standard deviation by its formula.
        double mean = average();
        double deviationSum = 0;
        for (double temperature: temperatureArray) {
            deviationSum += (temperature - mean) * (temperature - mean);
        }
        return Math.sqrt(deviationSum / arraySize);
    }

    public double min() {
        checkIfNotEmpty();

        double minTemperature = temperatureArray[0];

        for (double temperature: temperatureArray) {
            if (temperature < minTemperature) {
                minTemperature = temperature;
            }
        }

        return minTemperature;
    }

    public double max() {
        checkIfNotEmpty();

        double maxTemperature = temperatureArray[0];

        for (double temperature: temperatureArray) {
            if (temperature > maxTemperature) {
                maxTemperature = temperature;
            }
        }

        return maxTemperature;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkIfNotEmpty();

        double closest = temperatureArray[0];
        double closestDeviation = temperatureArray[0] - tempValue;

        for (double temperature: temperatureArray) {
            double deviation = temperature - tempValue;
            if (Math.abs(deviation) < Math.abs(closestDeviation)) {
                closestDeviation = deviation;
                closest = temperature;
            } else if (Math.abs(deviation) == Math.abs(closestDeviation)) {
                if (deviation > closestDeviation) {
                    closestDeviation = deviation;
                    closest = temperature;
                }
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTemps(tempValue, false);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTemps(tempValue, true);
    }

    public double[] findTemps(double tempValue, boolean greater) {
        int great;
        if (greater) {
            great = 1;
        } else {
            great = -1;
        }
        // Determine the amount of numbers
        int counter = 0;
        for (double temperature: temperatureArray) {
            if (Double.compare(temperature, tempValue) * great > 0) {
                counter++;
            }
        }

        // Create array with respective size
        double[] newArray = new double[counter];

        int i = 0;
        for (double temperature: temperatureArray) {
            if (Double.compare(temperature, tempValue) * great > 0) {
                newArray[i] = temperature;
                i++;
            }
        }
        return newArray;
    }

    public TempSummaryStatistics summaryStatistics() {
        checkIfNotEmpty();

        double avgTemp = average();
        double devTemp = deviation();
        double maxTemp = max();
        double minTemp = min();
        System.out.println(avgTemp + " " + devTemp + " "
                + maxTemp + " " + minTemp);
        return new TempSummaryStatistics(avgTemp, devTemp, maxTemp, minTemp);
    }

    public double addTemps(double... temps) {
        int newDataLength = temps.length;

        // Calculate new size for the temperature array.
        int newSize = arraySize;
        while (arraySize + newDataLength > newSize) {
            newSize *= 2;
        }

        // Create new bigger array
        double[] newTemperaturesArray = new double[newSize];

        // Insert the new data
        int i = 0;
        double sumOfNewTemps = 0;
        for (double existingTemp: temperatureArray) {
            newTemperaturesArray[i] = existingTemp;
            i++;
            sumOfNewTemps += existingTemp;
        }

        for (double temp: temps) {
            newTemperaturesArray[i] = temp;
            i++;
            sumOfNewTemps += temp;
        }

        temperatureArray = newTemperaturesArray;
        arraySize = i;

        return sumOfNewTemps;
    }

    private void checkIfNotEmpty() {
        // Check the input
        if (arraySize == 0) {
            throw new IllegalArgumentException();
        }
    }

}
