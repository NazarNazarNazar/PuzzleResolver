package com.example.puzzle;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        PuzzleResolver resolver = new MyPuzzleResolver();

        System.out.println(Arrays.toString(resolver.resolve(new int[]{1, 2, 3, 4, 0, 5, 6, 7})));
        System.out.println(Arrays.toString(resolver.resolve(new int[]{2, 1, 3, 4, 0, 5, 6, 7})));
        System.out.println(Arrays.toString(resolver.resolve(new int[]{0, 1, 2, 3, 4, 5, 6, 7})));

        System.out.println(Arrays.toString(resolver.resolve(new int[]{0, 1, 2, 3, 4, 5})));
//        System.out.println(Arrays.toString(resolver.resolve(null)));
    }
}
