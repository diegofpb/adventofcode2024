package es.diegofpb;

import es.diegofpb.utils.SharedUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class HistorianHysteriaApp {

    private static final List<Integer> locationsIdsFirst = new ArrayList<>();
    private static final List<Integer> locationsIdsSecond = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        var inputStream = SharedUtils.loadFile(HistorianHysteriaApp.class.getClassLoader(), "aoc-inputfile-01.txt");

        loadDataFromInputStream(inputStream);

        locationsIdsFirst.sort(Comparator.naturalOrder());
        locationsIdsSecond.sort(Comparator.naturalOrder());

        calcFirstResult();
        calcSecondResult();
    }

    /**
     *  Calculates the sum of the absolute differences between the corresponding elements.
     */
    private static void calcFirstResult() {
        int result = IntStream.range(0, locationsIdsFirst.size())
                .map(i -> Math.abs(locationsIdsFirst.get(i) - locationsIdsSecond.get(i)))
                .sum();
        System.out.println(result);
    }

    /**
     *  Multiplies the elements of the first list by their frequency in the second list.
     */
    private static void calcSecondResult() {
        Map<Integer, Long> countMap = locationsIdsSecond.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));

        int result = locationsIdsFirst.stream()
                .mapToInt(i -> (int) (i * countMap.getOrDefault(i, 0L)))
                .sum();

        System.out.println(result);
    }

    private static void loadDataFromInputStream(final InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(" {3}");
                if (split.length != 2) {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }
                locationsIdsFirst.add(Integer.valueOf(split[0]));
                locationsIdsSecond.add(Integer.valueOf(split[1]));
            }
        }
    }

}