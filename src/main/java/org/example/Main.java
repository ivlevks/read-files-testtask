package org.example;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        List<String> inputFileName = new ArrayList<>();
        String outFileName = null;

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                switch (arg) {
                    case "-d": Utils.setORDERING("DESC"); break;
                    case "-s" : Utils.setDataType("STRING"); break;
                    case "-i" : Utils.setDataType("INTEGER"); break;
                }
                continue;
            }
            if (outFileName == null) {
                outFileName = arg;
                continue;
            }
            inputFileName.add(arg);
        }

        System.out.println("Parameters: ");
        System.out.println(Utils.getORDERING() + "   " + Utils.getDataType());
        System.out.println(outFileName);
        inputFileName.forEach(e -> System.out.print(e + "   "));
        System.out.println();
        System.out.println();
        System.out.println();


        MergeAlgorithm mergeAlgorithm = new MergeAlgorithm();
        List<List<?>> list = new ArrayList<>();
        // обработать если список только 1
        list.add(Arrays.asList(1, 2, 3, 0, -1, -6, -123, -56789, -1235435435));
        list.add(Arrays.asList(36, 35, 34, 2123));
        list.add(Arrays.asList(145, 167, 312, 480, 512));
        list.add(Arrays.asList(20, 20, 30, 40, 45));
        list.add(Arrays.asList(1200, 2900, -2950, -4280, 15000));
        list.add(Arrays.asList(120, 2900, -3584, 4283, 150001));
        list.add(Arrays.asList(14, -121, 3680, 4590, 15000000));


//        list.add(Arrays.asList("a", "fi", "ml", "nm", "zb"));
//        list.add(Arrays.asList("b", "bg", "c", "deu", "ijt", "yutfd"));
//        list.add(Arrays.asList("s", "asd", "jkl", "yi yu"));
////        list.add(Arrays.asList("qwe", "iuyu", "qweqw", "iuough", "cvxvfghj"));
////        list.add(Arrays.asList("10", "123", "dqwe", "qwd 12v", "dasdvwer"));


        mergeAlgorithm.runMerge(list);

        System.out.println("Error Value");
        mergeAlgorithm.getErrorValueInteger().stream().forEach(System.out::println);
//        mergeAlgorithm.getErrorValueString().stream().forEach(System.out::println);

        System.out.println("Result Value");
        for (int i = 0; i < mergeAlgorithm.getResult().size(); i++) {
            if (Utils.getORDERING().equals("ASC")) {
                System.out.println(mergeAlgorithm.getResult().get(i));
            } else System.out.println(mergeAlgorithm.getResult().get(mergeAlgorithm.getResult().size() - 1 - i));
        }

    }

}