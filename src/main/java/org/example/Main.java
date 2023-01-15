package org.example;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        List<String> inputFileName = new ArrayList<>();
        String outFileName = null;

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                switch (arg) {
                    case "-d":
                        Utils.setOrdering("DESC");
                        break;
                    case "-s":
                        Utils.setDataType("STRING");
                        break;
                    case "-i":
                        Utils.setDataType("INTEGER");
                        break;
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
        System.out.println(Utils.getOrdering() + "   " + Utils.getDataType());
        System.out.println(outFileName);
        inputFileName.forEach(e -> System.out.print(e + "   "));
        System.out.println();
        System.out.println();


        Utils utils = new Utils();
        List<List<?>> list = new ArrayList<>();

        long LINES_TO_READ = 10_000_000;
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        int size = 1024 * 1024;

        for (String in : inputFileName) {
            List<Integer> midList = new ArrayList<>();
            Path pathToRead = null;
            try {
                pathToRead = utils.getFileURIFromResources(in);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try (BufferedReader br = new BufferedReader(new FileReader(pathToRead.toString(), StandardCharsets.UTF_8), size)) {
                br.lines().limit(LINES_TO_READ).forEach(s -> {
                    int num = Integer.parseInt(s);
                    midList.add(num);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add(midList);
        }




        MergeAlgorithm mergeAlgorithm = new MergeAlgorithm();

        // обработать если список только 1
//        list.add(Arrays.asList(1, 2, 3, 0, -1, -6, -123, -56789, -1235435435));
//        list.add(Arrays.asList(36, 35, 34, 2123));
//        list.add(Arrays.asList(145, 167, 312, 480, 512));
//        list.add(Arrays.asList(20, 20, 30, 40, 45));
//        list.add(Arrays.asList(1200, 2900, -2950, -4280, 15000));
//        list.add(Arrays.asList(120, 2900, -3584, 4283, 150001));
//        list.add(Arrays.asList(14, -121, 3680, 4590, 15000000));


//        list.add(Arrays.asList("a", "fi", "ml", "nm", "zb"));
//        list.add(Arrays.asList("b", "bg", "c", "deu", "ijt", "yutfd"));
//        list.add(Arrays.asList("s", "asd", "jkl", "yi yu"));
////        list.add(Arrays.asList("qwe", "iuyu", "qweqw", "iuough", "cvxvfghj"));
////        list.add(Arrays.asList("10", "123", "dqwe", "qwd 12v", "dasdvwer"));


        mergeAlgorithm.runMerge(list);

        System.out.println("Error Value");
        mergeAlgorithm.getErrorValueInteger().stream().forEach(System.out::println);
//        mergeAlgorithm.getErrorValueString().stream().forEach(System.out::println);
        System.out.println();
        System.out.println();

        System.out.println("Result Value");
        for (int i = 0; i < mergeAlgorithm.getResult().size(); i++) {
            if (Utils.getOrdering().equals("ASC")) {
                System.out.println(mergeAlgorithm.getResult().get(i));
            } else System.out.println(mergeAlgorithm.getResult().get(mergeAlgorithm.getResult().size() - 1 - i));
        }

        // исправить расположение файла
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(outFileName, StandardCharsets.UTF_8), size)) {
            for (Object o : mergeAlgorithm.getResult()) {
                wr.write(o.toString());
                wr.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}


//        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
//        List<Integer> integerList = new ArrayList<>();
//        try (RandomAccessFile reader = new RandomAccessFile(pathToRead.toFile(), "r")) {
//
//            FileChannel fileChannel = reader.getChannel();
//            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, fileChannel.size());
//            CharBuffer decodedBuffer = decoder.decode(buffer);
//
//            for (int i = 0; i < decodedBuffer.limit(); i++) {
//                char a = decodedBuffer.get();
//                integerList.add(Character.getNumericValue(a));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        integerList.forEach(System.out::println);