package org.example;

import java.util.*;

public class Main {

    private static String ORDERING;
    private static String DATA_TYPE;
    private static List<Integer> errorValueInteger = new ArrayList<>();
    private static List<String> errorValueString = new ArrayList<>();

    public static void main(String[] args) {

        ORDERING = "ASC";
        DATA_TYPE = "INTEGER";

        // обработать если список только 1
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 2, 3, 0, -1, 6, 123, 56789, 1235435435));
        list.add(Arrays.asList(36, 35, 34, 2123));
        list.add(Arrays.asList(145, 167, 312, 480, 512));
        list.add(Arrays.asList(20, 20, 30, 40, 45));
        list.add(Arrays.asList(1200, 2900, -2950, -4280, 15000));
        list.add(Arrays.asList(120, 2900, -3584, 4283, 150001));
        list.add(Arrays.asList(14, -121, 3680, 4590, 15000000));
        List<List<Integer>> midResult = recursiveMerge(list.subList(0, list.size() / 2), list.subList(list.size() / 2, list.size()));
        List<Integer> result = mergeWithErrorValue(midResult.get(0), errorValueInteger);

//        List<List<String>> list = new ArrayList<>();
//        list.add(Arrays.asList("a", "fi", "ml", "nm", "zb"));
//        list.add(Arrays.asList("b", "bg", "c", "deu", "ijt", "yutfd"));
//        list.add(Arrays.asList("s", "asd", "jkl", "yi yu"));
//        list.add(Arrays.asList("qwe", "iuyu", "qweqw", "iuough", "cvxvfghj"));
//        list.add(Arrays.asList("10", "123", "dqwe", "qwd 12v", "dasdvwer"));
//
//        List<List<String>> midResult = recursiveMerge(list.subList(0, list.size() / 2), list.subList(list.size() / 2, list.size()));
//        List<String> result = mergeWithErrorValue(midResult.get(0), errorValueString);



        System.out.println("Error Value");
        errorValueInteger.stream().forEach(System.out::println);
//        errorValueString.stream().forEach(System.out::println);

        System.out.println("Result Value");
        for (int i = 0; i < result.size(); i++) {
            if (ORDERING.equals("ASC")) {
                System.out.println(result.get(i));
            } else System.out.println(result.get(result.size() - 1 - i));
        }

    }


    private static <T> List<List<T>> recursiveMerge(List<List<T>> list1, List<List<T>> list2) {

        if (list1.size() == 1 && list2.size() == 1) {
            List<List<T>> result = new ArrayList<>();
            List<T> res = new ArrayList<>();

            if (DATA_TYPE.equals("INTEGER")) {
                mergeIntegerValue(list1, list2, res);
            } else mergeStringValue(list1, list2, res);

            result.add(res);
            return result;
        }

        List<List<T>> res1 = list1.size() == 1 ? list1 :
                recursiveMerge(list1.subList(0, list1.size() / 2), list1.subList(list1.size() / 2, list1.size()));
        List<List<T>> res2 = list2.size() == 1 ? list2 :
                recursiveMerge(list2.subList(0, list2.size() / 2), list2.subList(list2.size() / 2, list2.size()));

        return recursiveMerge(res1, res2);
    }

    private static <T> void mergeStringValue(List<List<T>> list1, List<List<T>> list2, List<T> res) {
        String prevElementList1 = "";
        String prevElementList2 = "";
        int i = 0;
        int j = 0;

        try {
            while (i <= list1.get(0).size() - 1 && j <= list2.get(0).size() - 1) {

                // i and j вылазят за массив, если до конца списка плохие входные данные - обработать
                while (checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                    errorValueString.add((String) list1.get(0).get(i++));
                }
                while (checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                    errorValueString.add((String) list2.get(0).get(j++));
                }

                if (compareString(list1.get(0).get(i), list2.get(0).get(j)) <= 0) {
                    prevElementList1 = (String) list1.get(0).get(i);
                    res.add(list1.get(0).get(i++));
                } else {
                    prevElementList2 = (String) list2.get(0).get(j);
                    res.add(list2.get(0).get(j++));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (i <= list1.get(0).size() - 1) {
            res.add(list1.get(0).get(i++));
        }
        while (j <= list2.get(0).size() - 1) {
            res.add(list2.get(0).get(j++));
        }
    }

    private static <T> void mergeIntegerValue(List<List<T>> list1, List<List<T>> list2, List<T> res) {
        int prevElementList1 = Integer.MIN_VALUE;
        int prevElementList2 = Integer.MIN_VALUE;
        int i = 0;
        int j = 0;

        try {
            while (i <= list1.get(0).size() - 1 && j <= list2.get(0).size() - 1) {

                // i and j вылазят за массив, если до конца списка плохие входные данные - обработать
                while (checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                    errorValueInteger.add((Integer) list1.get(0).get(i++));
                }
                while (checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                    errorValueInteger.add((Integer) list2.get(0).get(j++));
                }

                if ((int) list1.get(0).get(i) <= (int) list2.get(0).get(j)) {
                    prevElementList1 = (int) list1.get(0).get(i);
                    res.add(list1.get(0).get(i++));
                } else {
                    prevElementList2 = (int) list2.get(0).get(j);
                    res.add(list2.get(0).get(j++));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (i <= list1.get(0).size() - 1) {
            res.add(list1.get(0).get(i++));
        }
        while (j <= list2.get(0).size() - 1) {
            res.add(list2.get(0).get(j++));
        }
    }

    private static <T> List<T> mergeWithErrorValue(List<T> midResult, List<T> errorValue) {

        List<T> result = new ArrayList<>();

        Collections.sort(errorValue, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (DATA_TYPE.equals("INTEGER")) return (int) o1 - (int) o2;
                return compareString(o1, o2);
            }
        });

        int i = 0;
        int j = 0;

        while (i <= midResult.size() - 1 && j <= errorValue.size() - 1) {
            if (DATA_TYPE.equals("INTEGER")) {
                if ((int) midResult.get(i) <= (int) errorValue.get(j)) {
                    result.add(midResult.get(i++));
                } else result.add(errorValue.get(j++));
            } else {
                if (compareString(midResult.get(i), errorValue.get(j)) <= 0) {
                    result.add(midResult.get(i++));
                } else result.add(errorValue.get(j++));
            }
        }

        while (i <= midResult.size() - 1) {
            result.add(midResult.get(i++));
        }
        while (j <= errorValue.size() - 1) {
            result.add(errorValue.get(j++));
        }
        return result;
    }

    private static <T> boolean checkIsErrorValue(T element, T prevElementList) {
        if (DATA_TYPE.equals("INTEGER")) return (int) prevElementList > (int) element;
        if (DATA_TYPE.equals("STRING") && checkHasWhitespace(element)) return true;
        if ((DATA_TYPE.equals("STRING")) && prevElementList.equals("")) return false;
        return compareString(element, prevElementList) < 0;
    }

    private static <T> boolean checkHasWhitespace(T element) {
        char[] chars = ((String) element).toCharArray();
        for (char c : chars) if (c == ' ') return true;
        return false;
    }

    // lexicographical order
    private static <T> int compareString(T element1, T element2) {
        char[] first = ((String) element1).toCharArray();
        char[] second = ((String) element2).toCharArray();

        if (first.length < second.length) return -1;
        if (first.length > second.length) return 1;

        for (int i = 0; i < first.length; i++) {
            if (first[i] < second[i]) return -1;
            if (first[i] > second[i]) return 1;
        }
        return 0;
    }


}