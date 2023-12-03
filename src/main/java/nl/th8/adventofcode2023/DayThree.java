package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree implements Day {
    public static final String NON_DOT_SYMBOL_REGEX = "(.*[^\\d\\s.]+.*)";
    public static final String GEAR_SYMBOL_REGEX = "(.*[*]+.*)";
    private final PuzzleInputParser puzzleInputParser;

    //For actual use
    public DayThree() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "3.txt"));
    }

    //For Unittesting
    public DayThree(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
    }

    public int solvePartOne() {
        int sumOfPartNumbers = 0;
        List<String> lines = puzzleInputParser.getInputAsStringList();
        Pattern partNumberPattern = Pattern.compile("([0-9]+)");

        for(int i = 0; i < lines.size(); i++) {
            Matcher partNumberMatcher = partNumberPattern.matcher(lines.get(i));
            while(partNumberMatcher.find()) {
                if(checkIsPartNumber(lines, i, partNumberMatcher.start(), partNumberMatcher.end())) {
                    sumOfPartNumbers += Integer.parseInt(partNumberMatcher.group());
                }
            }
        }

        System.out.println("The sum of part numbers is " + sumOfPartNumbers);
        return sumOfPartNumbers;
    }

    public int solvePartTwo() {
        Map<String, List<Integer>> mapOfGearedItems = new HashMap<>();
        List<String> lines = puzzleInputParser.getInputAsStringList();
        Pattern partNumberPattern = Pattern.compile("([0-9]+)");

        for(int i = 0; i < lines.size(); i++) {
            Matcher partNumberMatcher = partNumberPattern.matcher(lines.get(i));
            while(partNumberMatcher.find()) {
                Optional<String> gearId = getGearId(lines, i, partNumberMatcher.start(), partNumberMatcher.end());
                if(gearId.isPresent()) {
                    System.out.println(gearId.get());
                    if(mapOfGearedItems.containsKey(gearId.get()))
                        mapOfGearedItems.get(gearId.get()).add(Integer.parseInt(partNumberMatcher.group()));
                    else
                        mapOfGearedItems.put(gearId.get(), new ArrayList<>(List.of(Integer.parseInt(partNumberMatcher.group()))));
                }
            }
        }

        int sumOfGearRatios = mapOfGearedItems.values().stream()
                .filter(parts -> parts.size() == 2)
                .mapToInt(parts -> parts.stream().reduce(Math::multiplyExact).orElseThrow())
                .sum();

        System.out.println("The sum of gear ratio's is " + sumOfGearRatios);
        return sumOfGearRatios;
    }

    private boolean checkIsPartNumber(List<String> lines, int index, int start, int end) {
        //Check adjacent symbols on the previous row
        int safeStartIndex = Math.max(start-1, 0);
        int safeEndIndex = Math.min(end+1, lines.get(0).length());
        if(index >=1) {
            String lineAboveNumber = lines.get(index-1).substring(safeStartIndex, safeEndIndex);
            if(lineAboveNumber.matches(NON_DOT_SYMBOL_REGEX)) {
                return true;
            }
        }
        if(index+1 < lines.size()) {
            String lineBelowNumber = lines.get(index+1).substring(safeStartIndex, safeEndIndex);
            if(lineBelowNumber.matches(NON_DOT_SYMBOL_REGEX)) {
                return true;
            }
        }
        return lines.get(index).substring(safeStartIndex, safeEndIndex).matches(NON_DOT_SYMBOL_REGEX);
    }

    private Optional<String> getGearId(List<String> lines, int index, int start, int end) {
        //Check adjacent symbols on the previous row
        int safeStartIndex = Math.max(start-1, 0);
        int safeEndIndex = Math.min(end+1, lines.get(0).length());
        if(index >=1) {
            String lineAboveNumber = lines.get(index-1).substring(safeStartIndex, safeEndIndex);
            if(lineAboveNumber.matches(GEAR_SYMBOL_REGEX)) {
                return Optional.of("L" + (index-1) + "G" + (safeStartIndex + lineAboveNumber.indexOf("*")));
            }
        }
        if(index+1 < lines.size()) {
            String lineBelowNumber = lines.get(index+1).substring(safeStartIndex, safeEndIndex);
            if(lineBelowNumber.matches(GEAR_SYMBOL_REGEX)) {
                return Optional.of("L" + (index+1) + "G" + (safeStartIndex + lineBelowNumber.indexOf("*")));
            }
        }
        String numberWithBorders = lines.get(index).substring(safeStartIndex, safeEndIndex);
        if(numberWithBorders.matches(GEAR_SYMBOL_REGEX))
            return Optional.of("L" + index + "G" + (safeStartIndex + numberWithBorders.indexOf("*")));
        return Optional.empty();
    }

    public int getDayNumber() {
        return 3;
    }
}
