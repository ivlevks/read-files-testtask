package org.example;

import java.nio.CharBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    private static String ORDERING = "ASC";
    private static String DATA_TYPE;

    public static String getOrdering() {
        return ORDERING;
    }

    public static void setOrdering(String ORDERING) {
        Utils.ORDERING = ORDERING;
    }

    public static String getDataType() {
        return DATA_TYPE;
    }

    public static void setDataType(String dataType) {
        DATA_TYPE = dataType;
    }


    Path getFileURIFromResources(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        return Paths.get(classLoader.getResource(fileName).getPath());
    }




}
