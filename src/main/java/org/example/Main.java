package org.example;

import java.util.*;

public class Main {


    public static void main(String[] args) {

        Utils.setORDERING("ASC");
        Utils.setDataType("STRING");

        MergeAlgorithm mergeAlgorithm = new MergeAlgorithm();
        // обработать если список только 1
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1, 2, 3, 0, -1, 6, 123, 56789, 1235435435));
//        list.add(Arrays.asList(36, 35, 34, 2123));
//        list.add(Arrays.asList(145, 167, 312, 480, 512));
//        list.add(Arrays.asList(20, 20, 30, 40, 45));
//        list.add(Arrays.asList(1200, 2900, -2950, -4280, 15000));
//        list.add(Arrays.asList(120, 2900, -3584, 4283, 150001));
//        list.add(Arrays.asList(14, -121, 3680, 4590, 15000000));
//        List<List<Integer>> midResult = mergeAlgorithm.recursiveMerge(list.subList(0, list.size() / 2), list.subList(list.size() / 2, list.size()));
//        List<Integer> result = mergeAlgorithm.mergeWithErrorValue(midResult.get(0), MergeAlgorithm.errorValueInteger);

        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("a", "fi", "ml", "nm", "zb"));
        list.add(Arrays.asList("b", "bg", "c", "deu", "ijt", "yutfd"));
        list.add(Arrays.asList("s", "asd", "jkl", "yi yu"));
        list.add(Arrays.asList("qwe", "iuyu", "qweqw", "iuough", "cvxvfghj"));
        list.add(Arrays.asList("10", "123", "dqwe", "qwd 12v", "dasdvwer"));

        List<List<String>> midResult = mergeAlgorithm.recursiveMerge(list.subList(0, list.size() / 2), list.subList(list.size() / 2, list.size()));
        List<String> result = mergeAlgorithm.mergeWithErrorValue(midResult.get(0), mergeAlgorithm.errorValueString);


        System.out.println("Error Value");
//      mergeAlgorithm.errorValueInteger.stream().forEach(System.out::println);
        mergeAlgorithm.errorValueString.stream().forEach(System.out::println);

        System.out.println("Result Value");
        for (int i = 0; i < result.size(); i++) {
            if (Utils.getORDERING().equals("ASC")) {
                System.out.println(result.get(i));
            } else System.out.println(result.get(result.size() - 1 - i));
        }

    }

}