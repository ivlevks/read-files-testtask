package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Utils utils = new Utils();
        utils.setParameters(args);

        List<List<Object>> listDataAllFiles = new ArrayList<>();
        utils.readFiles(listDataAllFiles);

        MergeAlgorithm<Object> mergeAlgorithm = new MergeAlgorithm<>();
        mergeAlgorithm.runMerge(listDataAllFiles);

        List<Object> result = mergeAlgorithm.getResult();
        utils.writeData(result);
    }
}