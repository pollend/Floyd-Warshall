package com.floyed;

import java.io.FileNotFoundException;

public class Main {
    public static void Print(int[][] w) {
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w.length; j++) {
                if (w[i][j] == Integer.MAX_VALUE)
                    System.out.print("inf,");
                else
                    System.out.print(w[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");

    }

    public static void main(String[] args) throws FileNotFoundException {
        int[][] weight = Parser.parse("test.txt");
        for (int k = 0; k < weight.length; k++) {
            for (int i = 0; i < weight.length; i++) {
                for (int j = 0; j < weight.length; j++) {
                    if (weight[i][k] == Integer.MAX_VALUE || weight[k][j] == Integer.MAX_VALUE)
                        continue;
                    if (weight[i][j] > weight[i][k] + weight[k][j])
                        weight[i][j] = weight[i][k] + weight[k][j];
                }
            }
        }
        Print(weight);
    }
}
