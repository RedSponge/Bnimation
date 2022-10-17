package com.redsponge.bnimation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestUtils {
    public static <T> T[][] cartesianProduct(T[][] options) {
        int numCombinations = Arrays.stream(options).mapToInt(a -> a.length).reduce(1, (a, b) -> a * b);

        T[][] output = (T[][]) new Object[numCombinations][options.length];

        for (int combination = 0; combination < numCombinations; combination++) {
            int j = combination;;

            for (int k = 0; k < options.length; k++) {
                output[combination][k] = options[k][j % options[0].length];
                j /= options[0].length;
            }
        }

        return output;
    }

    public static String readFile(String filename) {
        try {
            File f = new File(filename);
            StringBuilder builder = new StringBuilder();
            Scanner myReader = new Scanner(f);

            while (myReader.hasNextLine()) {
                builder.append(myReader.nextLine()).append('\n');
            }

            myReader.close();

            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Stream<Arguments> cartesianProductTestValues() {
        return Stream.of(
                arguments(
                        new Boolean[][]{
                                {false, true},
                                {false, true}
                        },
                        new Boolean[][]{
                                {false, false},
                                {true, false},
                                {false, true},
                                {true, true}
                        }),
                arguments(
                        new Integer[][] {
                                {1, 2, 3},
                                {4, 5, 6}
                        },
                        new Integer[][] {
                                {1, 4},
                                {2, 4},
                                {3, 4},
                                {1, 5},
                                {2, 5},
                                {3, 5},
                                {1, 6},
                                {2, 6},
                                {3, 6}
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("cartesianProductTestValues")
    void testCartesianProduct(Object[][] toMultiply, Object[][] result) {
        Object[][] prod = cartesianProduct(toMultiply);
        assertArrayEquals(result, prod);
    }

}
