package org.example;

import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static String ORDERING = "ASC";
    private static String DATA_TYPE;
    private final int size = 1024 * 1024;
    private final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
    private final List<String> inputFileName = new ArrayList<>();
    private String outFileName = null;


    public void setParameters(String[] args) {
        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                switch (arg) {
                    case "-d":
                        ORDERING = "DESC";
                        break;
                    case "-s":
                        DATA_TYPE = "STRING";
                        break;
                    case "-i":
                        DATA_TYPE = "INTEGER";
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
    }


    public void readFiles(List<List<Object>> list) {
        ClassLoader loader = Main.class.getClassLoader();
        String path = loader.getResource("org/example/Main.class").getPath();
        path = path.substring(0, (path).length() - 37);

        for (String in : inputFileName) {
            List<Object> midList = new ArrayList<>();
            StringBuilder pathToRead = new StringBuilder(path);
            pathToRead.append(in);

            try (BufferedReader br = new BufferedReader(new FileReader(pathToRead.toString(), StandardCharsets.UTF_8), size)) {
                long LINES_TO_READ = 10_000_000;
                br.lines().limit(LINES_TO_READ).forEach(s -> {
                    try {
                        if (s.length() != 0) {
                            if (DATA_TYPE.equals("INTEGER")) {
                                int num = Integer.parseInt(s);
                                midList.add(num);
                            } else {
                                midList.add(s);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage() + " invalid value for " + DATA_TYPE +
                                ". No possible compare. This value will lost.");
                    }
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            list.add(midList);
        }
    }

    public void writeData(List<Object> result) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(outFileName, StandardCharsets.UTF_8), size)) {
            for (int i = 0; i < result.size(); i++) {
                if (ORDERING.equals("ASC")) {
                    wr.write(result.get(i).toString());
                    wr.newLine();
                } else {
                    wr.write(result.get(result.size() - 1 - i).toString());
                    wr.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOrdering() {
        return ORDERING;
    }

    public static String getDataType() {
        return DATA_TYPE;
    }


}
