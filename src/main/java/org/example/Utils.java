package org.example;

public class Utils {
    private static String ORDERING = "ASC";
    private static String DATA_TYPE;

    public static String getORDERING() {
        return ORDERING;
    }

    public static void setORDERING(String ORDERING) {
        Utils.ORDERING = ORDERING;
    }

    public static String getDataType() {
        return DATA_TYPE;
    }

    public static void setDataType(String dataType) {
        DATA_TYPE = dataType;
    }
}
