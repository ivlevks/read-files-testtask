package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeAlgorithm<T> {
    private List<T> result = new ArrayList<>();
    private final List<T> errorValueInteger = new ArrayList<>();
    private final List<T> errorValueString = new ArrayList<>();

    public void runMerge(List<List<T>> list) {
        if (!list.isEmpty()) {
            List<List<T>> midResult = recursiveMerge(list.subList(0, list.size() / 2), list.subList(list.size() / 2, list.size()));

            if (Utils.getDataType().equals("INTEGER"))
                result = mergeWithErrorValue(midResult.get(0), errorValueInteger);
            else result = mergeWithErrorValue(midResult.get(0), errorValueString);

            if (Utils.getDataType().equals("INTEGER") && !Utils.getBigIntegers().isEmpty()) mergeWithBigIntegers();
        }
    }


    private List<List<T>> recursiveMerge(List<List<T>> list1, List<List<T>> list2) {
        if (list1.size() == 1 && list2.size() == 1) {
            List<List<T>> result = new ArrayList<>();
            List<T> res = new ArrayList<>();

            if (Utils.getDataType().equals("INTEGER")) {
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


    private void mergeStringValue(List<List<T>> list1, List<List<T>> list2, List<T> res) {
        String prevElementList1 = "";
        String prevElementList2 = "";
        int i = 0;
        int j = 0;

        try {
            while (i <= list1.get(0).size() - 1 && j <= list2.get(0).size() - 1) {

                while (i <= list1.get(0).size() - 1 &&
                        checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                    errorValueString.add(list1.get(0).get(i++));
                }
                while (j <= list2.get(0).size() - 1 &&
                        checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                    errorValueString.add(list2.get(0).get(j++));
                }

                if (i > list1.get(0).size() - 1 || j > list2.get(0).size() - 1) break;

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
            if (checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                errorValueString.add(list1.get(0).get(i++));
            } else {
                prevElementList1 = (String) list1.get(0).get(i);
                res.add(list1.get(0).get(i++));
            }

        }
        while (j <= list2.get(0).size() - 1) {
            if (checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                errorValueString.add(list2.get(0).get(j++));
            } else {
                prevElementList2 = (String) list2.get(0).get(j);
                res.add(list2.get(0).get(j++));
            }
        }
    }


    private void mergeIntegerValue(List<List<T>> list1, List<List<T>> list2, List<T> res) {
        int prevElementList1 = Integer.MIN_VALUE;
        int prevElementList2 = Integer.MIN_VALUE;
        int i = 0;
        int j = 0;

        try {
            while (i <= list1.get(0).size() - 1 && j <= list2.get(0).size() - 1) {

                while (i <= list1.get(0).size() - 1 &&
                        checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                    errorValueInteger.add(list1.get(0).get(i++));
                }
                while (j <= list2.get(0).size() - 1 &&
                        checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                    errorValueInteger.add(list2.get(0).get(j++));
                }

                if (i > list1.get(0).size() - 1 || j > list2.get(0).size() - 1) break;

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
            if (checkIsErrorValue(list1.get(0).get(i), prevElementList1)) {
                errorValueInteger.add(list1.get(0).get(i++));
            } else {
                prevElementList1 = (int) list1.get(0).get(i);
                res.add(list1.get(0).get(i++));
            }
        }

        while (j <= list2.get(0).size() - 1) {
            if (checkIsErrorValue(list2.get(0).get(j), prevElementList2)) {
                errorValueInteger.add(list2.get(0).get(j++));
            } else {
                prevElementList2 = (int) list2.get(0).get(j);
                res.add(list2.get(0).get(j++));
            }
        }
    }


    private List<T> mergeWithErrorValue(List<T> midResult, List<T> errorValue) {
        List<T> result = new ArrayList<>();

        errorValue.sort((o1, o2) -> {
            if (Utils.getDataType().equals("INTEGER")) return (int) o1 - (int) o2;
            return compareString(o1, o2);
        });

        int i = 0;
        int j = 0;
        while (i <= midResult.size() - 1 && j <= errorValue.size() - 1) {
            if (Utils.getDataType().equals("INTEGER")) {
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

    private void mergeWithBigIntegers() {
        List<BigInteger> list = Utils.getBigIntegers();
        Collections.sort(list);

        int pos = 0;
        for (BigInteger bigInteger : list) {
            if (bigInteger.compareTo(BigInteger.ZERO) < 0) {
                result.add(pos++, (T) bigInteger);
            } else {
                result.add(result.size(), (T) bigInteger);
            }
        }
    }


    private <T> boolean checkIsErrorValue(T element, T prevElementList) {
        if (Utils.getDataType().equals("INTEGER") && (int) prevElementList > (int) element) {
            System.out.println("Line has not sorted " + Utils.getDataType() + " value - " + element);
            return true;
        }
        if ((Utils.getDataType().equals("STRING")) && prevElementList.equals("")) return false;
        if (Utils.getDataType().equals("STRING") && checkHasWhitespace(element)) {
            System.out.println("Line has whitespace character - " + element);
            return true;
        }
        if (Utils.getDataType().equals("STRING") && (compareString(element, prevElementList) < 0)) {
            System.out.println("Line has not sorted " + Utils.getDataType() + " value - " + element);
            return true;
        }
        return false;
    }


    private <T> boolean checkHasWhitespace(T element) {
        char[] chars = ((String) element).toCharArray();
        for (char c : chars) if (c == ' ') return true;
        return false;
    }

    // lexicographical order
    private <T> int compareString(T element1, T element2) {
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

    public List<T> getResult() {
        return result;
    }
}
