package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DayOneTest {

    @Mock
    private PuzzleInputParser puzzleInputParser;

    @InjectMocks
    private DayOne dayOne;

    @Test
    void solvePartOne() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(Arrays.asList("1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"));
        assertEquals(142, dayOne.solvePartOne());
    }

    @Test
    void solvePart2() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(Arrays.asList("two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"));
        assertEquals(281, dayOne.solvePartTwo());
    }
}