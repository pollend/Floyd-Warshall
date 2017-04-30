package com.floyed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by michaelpollind on 4/29/17.
 */
public final class Parser {
    public static int[][] parse(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String[] keyMapping = scanner.nextLine().split(",");

        int[][] result = new int[keyMapping.length][keyMapping.length];

        for(int x = 0; x< keyMapping.length; x++)
        {
            for(int y = 0; y< keyMapping.length; y++)
            {
                result[x][y] = Integer.MAX_VALUE;
            }
            result[x][x] = 0;
        }

        Pattern p = Pattern.compile("([a-zA-Z ]+)->([a-zA-Z]+),(-?[0-9]+)");
        while (scanner.hasNext())
        {
            String line = scanner.nextLine().trim();
             Matcher match = p.matcher(line);
             match.find();
            if(!match.hitEnd() && match.groupCount() != 3)
                throw new RuntimeException("unknown transition: " + line);

            String currentState = match.group(1);
            String nextState = match.group(2);
            int weight = Integer.parseInt(match.group(3));

            result[getIndex(currentState,keyMapping)][getIndex(nextState,keyMapping)] = weight;
        }
        return result;
    }

    private static int getIndex(String s, String[] l)
    {
        for(int x = 0; x < l.length; x++)
        {
            if(s.equals(l[x])) {
                return x;
            }
        }
        return  -1;
    }

}
