package com.github.curriculeon;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                fizzBuzz(
                        IntStream.rangeClosed(1, 100).toArray(),
                        new LinkedHashMap<Predicate<Integer>, String>() {{
                            put(x1 -> x1 % 3 == 0, "Fizz");
                            put(x -> x % 5 == 0, "Buzz");
                        }}
                )
        );
    }

    private static String fizzBuzz(int[] arr, Map<Predicate<Integer>, String> preds) {
        return Arrays.stream(arr)
                     .mapToObj(x -> preds.entrySet().stream()
                                         .filter(entry -> entry.getKey().test(x))
                                         .map(Map.Entry::getValue)
                                         .reduce(String::concat)
                                         .orElse(String.valueOf(x)))
                     .collect(Collectors.joining(" "));
    }
}
