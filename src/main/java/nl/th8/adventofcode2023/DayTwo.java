package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwo implements Day {
    private final PuzzleInputParser puzzleInputParser;

    private final Map<String, Integer> partOneBounds = Map.of("red", 12, "green", 13, "blue", 14);

    //For actual use
    public DayTwo() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "2.txt"));
    }

    //For Unittesting
    public DayTwo(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
    }

    public int solvePartOne() {
        List<String> lines = puzzleInputParser.getInputAsStringList();
        int sumOfPossibleIds = 0;

        for(String line : lines) {
            //Split the game into it's ID and the game data
            String[] gameSplit = line.split(": ", 2);
            int gameId = Integer.parseInt(gameSplit[0].replace("Game ", ""));

            //Split the game data on all comma's and semicolons and check if any are out of the game bounds
            if(Arrays.stream(gameSplit[1].split("([,;] )"))
                    .noneMatch(cubeSet -> {
                        var cubeSetSplit = cubeSet.split(" ");
                        return partOneBounds.get(cubeSetSplit[1]) < Integer.parseInt(cubeSetSplit[0]);
                    })
            ) {
                sumOfPossibleIds += gameId;
            }
        }
        System.out.println("The sum of possible game id's is " + sumOfPossibleIds);
        return sumOfPossibleIds;
    }

    public int solvePartTwo() {
        List<String> lines = puzzleInputParser.getInputAsStringList();
        int sumOfAllPowerlevels = 0;

        for(String line : lines) {
            //Split the game into it's ID and the game data, discard the ID part.
            String[] gameSplit = line.split(": ", 2);
            Map<String, Integer> minimumCubesRequired = new HashMap<>();

            //Split the game data on all comma's and semicolons and save the amount of cubes to a map if they're bigger than the previous biggest set of a certain colour
            for(var cubeSet : gameSplit[1].split("([,;] )")) {
                var cubeSetSplit = cubeSet.split(" ");
                int amountOfCubes = Integer.parseInt(cubeSetSplit[0]);
                minimumCubesRequired.compute(cubeSetSplit[1], (key, oldValue) -> oldValue == null ? amountOfCubes : amountOfCubes > oldValue ? amountOfCubes : oldValue);
            }
            sumOfAllPowerlevels += minimumCubesRequired.values().stream().reduce(Math::multiplyExact).orElseThrow();
        }
        System.out.println("The sum of all power levels is " + sumOfAllPowerlevels);
        return sumOfAllPowerlevels;
    }

    public int getDayNumber() {
        return 2;
    }
}
