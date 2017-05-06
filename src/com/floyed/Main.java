package com.floyed;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public static void generate_hsv(String file,String name,Long[] e) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        for(int x = 0; x < e.length; x++)
        {
           writer.print(name + "    " + (e[x]/ 1000000.0) + "\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException {

        generate_hsv("result/small-1-100.hsv","small-1-100",performance("test/small/small-1-100.txt"));
        generate_hsv("result/small-2-100.hsv","small-2-100",performance("test/small/small-2-100.txt"));
        generate_hsv("result/small-4-100.hsv","small-4-100",performance("test/small/small-4-100.txt"));
        generate_hsv("result/small-8-100.hsv","small-8-100",performance("test/small/small-8-100.txt"));
        generate_hsv("result/small-16-100.hsv","small-16-100",performance("test/small/small-16-100.txt"));

        generate_hsv("result/medium-1-100.hsv","medium-1-100",performance("test/medium/medium-1-100.txt"));
        generate_hsv("result/medium-2-100.hsv","medium-2-100",performance("test/medium/medium-2-100.txt"));
        generate_hsv("result/medium-4-100.hsv","medium-4-100",performance("test/medium/medium-4-100.txt"));
        generate_hsv("result/medium-8-100.hsv","medium-8-100",performance("test/medium/medium-8-100.txt"));
        generate_hsv("result/medium-16-100.hsv","medium-16-100",performance("test/medium/medium-16-100.txt"));
        generate_hsv("result/medium-32-100.hsv","medium-32-100",performance("test/medium/medium-32-100.txt"));
        generate_hsv("result/medium-64-100.hsv","medium-64-100",performance("test/medium/medium-64-100.txt"));
        generate_hsv("result/medium-128-100.hsv","medium-128-100",performance("test/medium/medium-128-100.txt"));

        generate_hsv("result/large-1-100.hsv","large-1-100",performance("test/large/large-1-100.txt"));
        generate_hsv("result/large-2-100.hsv","large-2-100",performance("test/large/large-2-100.txt"));
        generate_hsv("result/large-4-100.hsv","large-4-100",performance("test/large/large-4-100.txt"));
        generate_hsv("result/large-6-100.hsv","large-6-100",performance("test/large/large-6-100.txt"));
        generate_hsv("result/large-8-100.hsv","large-8-100",performance("test/large/large-8-100.txt"));
        generate_hsv("result/large-16-100.hsv","large-16-100",performance("test/large/large-16-100.txt"));
        generate_hsv("result/large-32-100.hsv","large-32-100",performance("test/large/large-32-100.txt"));
        generate_hsv("result/large-64-100.hsv","large-64-100",performance("test/large/large-64-100.txt"));
        generate_hsv("result/large-128-100.hsv","large-128-100",performance("test/large/large-128-100.txt"));
        generate_hsv("result/large-256-100.hsv","large-256-100",performance("test/large/large-256-100.txt"));
        generate_hsv("result/large-512-100.hsv","large-512-100",performance("test/large/large-512-100.txt"));

        generate_hsv("result/verylarge-1-100.hsv","verylarge-1-100",performance("test/verylarge/verylarge-1-100.txt"));
        generate_hsv("result/verylarge-2-100.hsv","verylarge-2-100",performance("test/verylarge/verylarge-2-100.txt"));
        generate_hsv("result/verylarge-4-100.hsv","verylarge-4-100",performance("test/verylarge/verylarge-4-100.txt"));
        generate_hsv("result/verylarge-6-100.hsv","large-6-100",performance("test/verylarge/verylarge-6-100.txt"));
        generate_hsv("result/verylarge-8-100.hsv","verylarge-8-100",performance("test/verylarge/verylarge-8-100.txt"));
        generate_hsv("result/verylarge-16-100.hsv","verylarge-16-100",performance("test/verylarge/verylarge-16-100.txt"));
        generate_hsv("result/verylarge-32-100.hsv","verylarge-32-100",performance("test/verylarge/verylarge-32-100.txt"));
        generate_hsv("result/verylarge-64-100.hsv","verylarge-64-100",performance("test/verylarge/verylarge-64-100.txt"));
        generate_hsv("result/verylarge-128-100.hsv","verylarge-128-100",performance("test/verylarge/verylarge-128-100.txt"));
        generate_hsv("result/verylarge-256-100.hsv","verylarge-256-100",performance("test/verylarge/verylarge-256-100.txt"));
        generate_hsv("result/verylarge-512-100.hsv","verylarge-512-100",performance("test/verylarge/verylarge-512-100.txt"));
        generate_hsv("result/verylarge-1024-100.hsv","verylarge-1024-100",performance("test/verylarge/verylarge-1024-100.txt"));
        generate_hsv("result/verylarge-2048-100.hsv","verylarge-2048-100",performance("test/verylarge/verylarge-2048-100.txt"));
    }

    public static void output(PrintWriter p,int number_of_entries,int numberOfConnection,long[] item)
    {
        for(int x = 0; x < item.length; x++)
            p.println(number_of_entries + "," + numberOfConnection + "," + item[x]);
    }

    public static Long[] performance(String file) throws FileNotFoundException {
        int[][] weight = Parser.parse(file);
        Long[] result = new Long[1000];
        for (int x = 0; x< 1000; x++) {
            int[][] temp = duplicate(weight);
            long startTime = System.nanoTime();
            floydWarshall(temp);
            long stopTime = System.nanoTime();
            result[x] = stopTime - startTime;
        }
        return result;
    }

    public  static  int[][] duplicate (int[][] k)
    {
        int [][] item = new int[k.length][k.length];
        for(int x = 0; x < k.length; x++)
        {
            for(int y = 0; y < k.length; y++)
            {
                item[x][y] = k[x][y];
            }
        }
        return  item;
    }

    public  static void floydWarshall(int[][] weight)
    {
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

    }
}
