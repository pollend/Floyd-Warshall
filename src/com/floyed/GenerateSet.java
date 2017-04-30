package com.floyed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by michaelpollind on 4/29/17.
 */
public class GenerateSet {
    public static void main(String[] args) throws IOException {
        String small_template = "test/small/small-%1s-%2s.txt";
        String[] small_mapping_template = new String[]{"a","b","c","d","e","f"};
        Generate(small_template,small_mapping_template,1,100);
        Generate(small_template,small_mapping_template,2,100);
        Generate(small_template,small_mapping_template,4,100);
        Generate(small_template,small_mapping_template,6,100);
        Generate(small_template,small_mapping_template,8,100);
        Generate(small_template,small_mapping_template,10,100);

        String medium_template = "test/medium/medium-%1s-%2s.txt";
        String[] medium_mapping_template = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
        Generate(medium_template,medium_mapping_template,1,100);
        Generate(medium_template,medium_mapping_template,2,100);
        Generate(medium_template,medium_mapping_template,4,100);
        Generate(medium_template,medium_mapping_template,6,100);
        Generate(medium_template,medium_mapping_template,8,100);
        Generate(medium_template,medium_mapping_template,10,100);

        String large_template = "test/large/large-%1s-%2s.txt";
        String[] large_mapping_template = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
        Generate(large_template,large_mapping_template,1,100);
        Generate(large_template,large_mapping_template,2,100);
        Generate(large_template,large_mapping_template,4,100);
        Generate(large_template,large_mapping_template,6,100);
        Generate(large_template,large_mapping_template,8,100);
        Generate(large_template,large_mapping_template,20,100);
        Generate(large_template,large_mapping_template,50,100);
        Generate(large_template,large_mapping_template,70,100);
        Generate(large_template,large_mapping_template,80,100);
        Generate(large_template,large_mapping_template,90,100);
        Generate(large_template,large_mapping_template,100,100);
        Generate(large_template,large_mapping_template,200,100);

    }

    public  static  void  Generate(String file,String[] mapping,int numberOfConnection,int maxWeight) throws FileNotFoundException {
        HashSet<String> verify = new HashSet<>();

        String f = String.format(file,numberOfConnection,maxWeight);
        PrintWriter writer = new PrintWriter(f);

        writer.write(join(mapping) + "\n");
        Random r = new Random();
        for(int x = 0; x<= numberOfConnection; x++)
        {

            int count = 0;
            String  first = "";
            String  next = "";
            String key = "";
            do
            {
                first = mapping[r.nextInt(mapping.length - 1)];
                next = mapping[r.nextInt(mapping.length - 1)];
                if(first.equals(next))
                    continue;
                key = first + "," + next;
            }
            while(verify.contains(key));

            verify.add(key);

            int weight = r.nextInt(maxWeight);
            writer.append(first + "->" + next + "," + weight + "\n");

            System.out.println(f + ":"+x + "/" + numberOfConnection);
        }
        System.out.println("-----------------------------------------");

        writer.close();
    }

    public static String join(String[] c )
    {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < c.length; i++)
        {
            result.append(c[i]);
            if(i == c.length-1)
                continue;
            result.append(",");
        }
        return result.toString();
    }
}
