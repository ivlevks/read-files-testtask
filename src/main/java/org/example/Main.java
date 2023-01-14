package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static String ORDERING;
    private static List<Integer> errorValue = new ArrayList<>();

    public static void main(String[] args) {

        ORDERING = "ASC";
        List<List<Integer>> list = new ArrayList<>();

        // обработать если список только 1
        list.add(Arrays.asList(1, 2, 3, 0, -1, 6, 123, 56789, 1235435435));
        list.add(Arrays.asList(36, 35, 34, 2123));
        list.add(Arrays.asList(145, 167, 312, 480, 512));
        list.add(Arrays.asList(20, 20, 30, 40, 45));
        list.add(Arrays.asList(1200, 2900, -2950, -4280, 15000));
        list.add(Arrays.asList(120, 2900, -3584, 4283, 150001));
        list.add(Arrays.asList(14, -121, 3680, 4590, 15000000));

        List<List<Integer>> midResult = recursiveMerge(list.subList(0, list.size() / 2), list.subList(list.size() /2, list.size()));
        List<Integer> result = mergeWithErrorValue(midResult.get(0), errorValue);

        System.out.println("Error Value");
        errorValue.stream().forEach(System.out::println);

        System.out.println("Result Value");
        for (int i = 0; i < result.size(); i++) {
            if (ORDERING.equals("ASC")) {
                System.out.println(result.get(i));
            } else System.out.println(result.get(result.size() - 1 - i));
        }


//        List<List<String>> listStr = new ArrayList<>();
//        listStr.add(Arrays.asList("a", "b", "c", "d", "e"));
//        listStr.add(Arrays.asList("asdsd", "asdds", "jklu", "yiyu"));
//        listStr.add(Arrays.asList("qwe", "iuyui", "qweqw", "iuou", "cvxv"));
//        listStr.add(Arrays.asList("10", "123", "dqwe", "qwd12v", "dasdv"));
//
//        List<List<String>> result = recursiveMergeString(listStr.subList(0, listStr.size() / 2), listStr.subList(listStr.size() /2, listStr.size()));
//
//        for (List<String> strings : result) {
//            System.out.println(strings);
//        }

    }

    private static List<List<Integer>> recursiveMerge(List<List<Integer>> list1, List<List<Integer>> list2) {

        if (list1.size() == 1 && list2.size() == 1) {

            List<List<Integer>> result = new ArrayList<>();
            List<Integer> res = new ArrayList<>();
            int prevElementList1 = Integer.MIN_VALUE;
            int prevElementList2 = Integer.MIN_VALUE;
            int i = 0;
            int j = 0;

            try {

                while (i <= list1.get(0).size() - 1 && j <= list2.get(0).size() - 1) {
                    // создать метод compare для Стрингов и сравнивать массивы chars с помощью two pointers
                    // также вставить дженерики

                    // find error Value, add it in errorValue List, skip it.
                    while (checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                        errorValue.add(list1.get(0).get(i++));
                    }
                    while (checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                        errorValue.add(list2.get(0).get(j++));
                    }

                    if (list1.get(0).get(i) <= list2.get(0).get(j)) {
                        prevElementList1 = list1.get(0).get(i);
                        res.add(list1.get(0).get(i++));
                    } else {
                        prevElementList2 = list2.get(0).get(j);
                        res.add(list2.get(0).get(j++));
                    }
                }

                while (i <= list1.get(0).size() - 1) {
                    res.add(list1.get(0).get(i++));
                }
                while (j <= list2.get(0).size() - 1) {
                    res.add(list2.get(0).get(j++));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            result.add(res);
            return result;
        }

        List<List<Integer>> res1 = list1.size() == 1 ? list1 :
                recursiveMerge(list1.subList(0, list1.size() / 2), list1.subList(list1.size() / 2, list1.size()));
        List<List<Integer>> res2 = list2.size() == 1 ? list2 :
                recursiveMerge(list2.subList(0, list2.size() / 2), list2.subList(list2.size() / 2, list2.size()));

        return recursiveMerge(res1, res2);
    }

    private static List<Integer> mergeWithErrorValue(List<Integer> midResult, List<Integer> errorValue) {

        List<Integer> result = new ArrayList<>();
        Collections.sort(errorValue);
        int i = 0;
        int j = 0;

        while (i <= midResult.size() - 1 && j <= errorValue.size() - 1) {
            if (midResult.get(i) <= errorValue.get(j)) {
                result.add(midResult.get(i++));
            } else result.add(errorValue.get(j++));
        }

        while (i <= midResult.size() - 1) {
            result.add(midResult.get(i++));
        }
        while (j <= errorValue.size() - 1) {
            result.add(errorValue.get(j++));
        }
        return result;
    }

    private static boolean checkIsErrorValue(Integer num, int prevElementList) {
        return prevElementList > num;
    }









}