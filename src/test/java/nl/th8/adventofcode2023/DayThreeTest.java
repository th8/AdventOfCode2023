package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayThreeTest {

    @Mock
    private PuzzleInputParser puzzleInputParser;

    @InjectMocks
    private DayThree day;

    @Test
    void solvePartOne() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(Arrays.asList("467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."));
        assertEquals(4361, day.solvePartOne());
    }

    @Test
    void solvePart2() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(Arrays.asList("467..114..",
                "*.........",
                "35....633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.....*",
                ".664...598"));
        assertEquals(467835, day.solvePartTwo());
    }
}