package org.example;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static String ORDERING = "ASC";
    private static String DATA_TYPE;
    private final int bufferSize = 1024 * 1024;
    private final List<String> inputFileName = new ArrayList<>();
    private String outFileName = null;
    private static final List<BigInteger> bigIntegers = new ArrayList<>();


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

        for (String in : inputFileName) {
            List<Object> midList = new ArrayList<>();
            InputStream inputStream = this.getClass()
                    .getClassLoader().getResourceAsStream(in);

            if (inputStream != null) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8), bufferSize)) {

                    long LINES_TO_READ = 10_000_000;
                    br.lines().limit(LINES_TO_READ).forEach(s -> {
                        try {
                            if (s.length() != 0) {
                                if (DATA_TYPE.equals("INTEGER")) {
                                    try {
                                        int num = Integer.parseInt(s);
                                        midList.add(num);
                                    } catch (NumberFormatException m) {
                                        bigIntegers.add(new BigInteger(s));
                                    }
                                } else {
                                    midList.add(s);
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage() + ": " + s + " invalid value for " + DATA_TYPE +
                                    ". No possible compare. This value will lost.");
                        }
                    });
                } catch (IOException m) {
                    System.out.println(m.getMessage());
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            list.add(midList);
        }
    }


    public void writeData(List<Object> result) {
        if (outFileName == null) System.out.println("Out file name not found");
        else {
            try (BufferedWriter wr = new BufferedWriter(new FileWriter(outFileName, StandardCharsets.UTF_8), bufferSize)) {
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
    }


    public static String getDataType() {
        return DATA_TYPE;
    }

    public static List<BigInteger> getBigIntegers() {
        return bigIntegers;
    }
}
