package com.example.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyPuzzleResolver implements PuzzleResolver {

    public static final int EXPECTED = 12340567;

    @Override
    public int[] resolve(int[] start) {
        if (!isArrValid(start)) {
            throw new RuntimeException("Array isn't valid");
        }

        int beginning = arrayToInt(start);

        if (beginning == EXPECTED) {
            return new int[0];
        }

        LinkedList<Integer> available = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> path = new HashMap<>();

        available.add(beginning);
        visited.add(beginning);

        while (available.size() > 0) {
            int current = available.remove();

            List<Integer> siblings = getSiblings(intToArray(current));
            for (int sibling : siblings) {
                if (visited.contains(sibling)) {
                    continue;
                }

                visited.add(sibling);
                available.add(sibling);

                path.put(sibling, current);

                if (sibling == EXPECTED) {
                    available.clear();
                    break;
                }
            }
        }

        return getRoute(beginning, path);
    }

    private static int[] getRoute(int beginning, Map<Integer, Integer> path) {
        List<Integer> route = new ArrayList<>();

        int curr = EXPECTED;

        while (curr != beginning) {
            int prev = path.get(curr);

            int[] currArr = intToArray(curr);
            int[] prevArr = intToArray(prev);
            int idx = zero(currArr);

            route.add(prevArr[idx]);
            curr = prev;
        }

        Collections.reverse(route);

        return listToArray(route);
    }

    private static int getSibling(int[] arr, int parent, int sibling) {
        return arrayToInt(swap(arr, parent, sibling));
    }

    private static List<Integer> getSiblings(int[] state) {
        List<Integer> siblings = new ArrayList<>();

        switch (zero(state)) {
            case 0:
                siblings.add(getSibling(state, 0, 1));
                siblings.add(getSibling(state, 0, 2));
                break;
            case 1:
                siblings.add(getSibling(state, 1, 0));
                siblings.add(getSibling(state, 1, 2));
                siblings.add(getSibling(state, 1, 3));
                break;
            case 2:
                siblings.add(getSibling(state, 2, 0));
                siblings.add(getSibling(state, 2, 1));
                siblings.add(getSibling(state, 2, 5));
                break;
            case 3:
                siblings.add(getSibling(state, 3, 1));
                siblings.add(getSibling(state, 3, 4));
                siblings.add(getSibling(state, 3, 6));
                break;
            case 4:
                siblings.add(getSibling(state, 4, 3));
                siblings.add(getSibling(state, 4, 5));
                break;
            case 5:
                siblings.add(getSibling(state, 5, 2));
                siblings.add(getSibling(state, 5, 4));
                siblings.add(getSibling(state, 5, 7));
                break;
            case 6:
                siblings.add(getSibling(state, 6, 3));
                siblings.add(getSibling(state, 6, 7));
                break;
            case 7:
                siblings.add(getSibling(state, 7, 6));
                siblings.add(getSibling(state, 7, 5));
                break;
        }

        return siblings;
    }

    private static int[] swap(int[] arr, int from, int to) {
        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        int tmp = result[from];
        result[from] = result[to];
        result[to] = tmp;

        return result;
    }

    private static int zero(int[] arr) {
        return Arrays
                .stream(arr)
                .filter(idx -> arr[idx] == 0)
                .findFirst()
                .orElse(-1);
    }

    private static boolean isArrValid(int[] arr) {
        return arr != null && arr.length == 8;
    }

    private static int arrayToInt(int[] arr) {
        int result = 0;
        for (int value : arr) {
            result += value;
            result *= 10;
        }
        return result / 10;
    }

    private static int[] intToArray(int source) {
        int[] arr = new int[8];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = source % 10;
            source /= 10;
        }
        return arr;
    }

    private static int[] listToArray(List<Integer> list) {
        return list
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
