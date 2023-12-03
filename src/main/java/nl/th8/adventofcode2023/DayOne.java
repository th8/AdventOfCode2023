package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayOne implements Day {
    private final PuzzleInputParser puzzleInputParser;

    //For actual use
    public DayOne() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "1.txt"));
    }

    //For Unittesting
    public DayOne(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
    }

    public int solvePartOne() {
        Pattern numberPattern = Pattern.compile("([0-9])");
        int calibrationValue = calculateCalibrationValueForPattern(numberPattern);

        System.out.println("Calibration value for part 1 is " + calibrationValue);
        return calibrationValue;
    }

    public int solvePartTwo() {
        Pattern numberPattern = Pattern.compile("([0-9]|one|two|three|four|five|six|seven|eight|nine)");

        int calibrationValue = calculateCalibrationValueForPattern(numberPattern);

        System.out.println("Calibration value for part 2 is " + calibrationValue);
        return calibrationValue;
    }

    private int calculateCalibrationValueForPattern(Pattern numberPattern) {
        int calibrationValue = 0;

        var lines = puzzleInputParser.getInputAsStringList();
        for(var line : lines) {
            Matcher firstNumberMatcher = numberPattern.matcher(line);
            int first;
            int last;


            if(firstNumberMatcher.find()) {
                first = stringDigitToInt(firstNumberMatcher.group());
                last = first;
            }
            else
                throw new IllegalArgumentException("No first number in line");

            //Start searching at previousMatch' start+1 to allow for 'oneight' or 'twone' to correctly match.
            while(firstNumberMatcher.find(firstNumberMatcher.start()+1))
                last = stringDigitToInt(firstNumberMatcher.group());

            calibrationValue += Integer.parseInt(first + String.valueOf(last));
        }

        return calibrationValue;
    }

    public int getDayNumber() {
        return 1;
    }

    private int stringDigitToInt(String digit) {
        switch (digit) {
            case "zero" -> {
                return 0;
            }
            case "one" -> {
                return 1;
            }
            case "two" -> {
                return 2;
            }
            case "three" -> {
                return 3;
            }
            case "four" -> {
                return 4;
            }
            case "five" -> {
                return 5;
            }
            case "six" -> {
                return 6;
            }
            case "seven" -> {
                return 7;
            }
            case "eight" -> {
                return 8;
            }
            case "nine" -> {
                return 9;
            }
            default -> {
                return Integer.parseInt(digit);
            }
        }
    }

}
