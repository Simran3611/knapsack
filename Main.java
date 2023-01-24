package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static final int totalItems = 0;
    public static final int totalCapacity = 0;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String selectedFile = "";
        System.out.println("Type the file name: ");
        selectedFile = kb.next();

        int size = getTotalItems(selectedFile) + 1;
        System.out.println("Size: " + size);
        int capacity = getTotalCapacity(selectedFile);
        System.out.println("Weight capacity: " + capacity);

        int val[] = new int[size];

        System.out.println("------------------- Val ---------------------");
        val = readFileVal(selectedFile, val);
        for(int i = 0; i < val.length; i++) {
            System.out.println(val[i]);
        }

        int wt[] = new int[size];
        System.out.println("------------------- Weight ---------------------");
        wt = readFileWt(selectedFile, wt);
        for(int i = 0; i < wt.length; i++) {
            System.out.println(wt[i]);
        }

        int n = val.length;
        Knapsack sack = new Knapsack();

        sack.printknapSack(capacity, wt, val, n);
    }
    public static int getTotalItems(String filename) {
        Scanner fin = null;
        int i = 0;
        try {
            fin = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }
        String currentVal = "";

        while(fin.hasNextLine()) {
            i = fin.nextInt();
            break;
        }
        fin.close();
        return i;
    }
    public static int getTotalCapacity(String filename) {
        Scanner fin = null;
        int i = 0;
        try {
            fin = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }
        String currentVal = "";

        while(fin.hasNextLine()) {
            i = fin.nextInt();
            i = fin.nextInt();
            break;
        }
        fin.close();
        return i;
    }
    public static int[] readFileWt(String filename, int[]val) {
        Scanner fin = null;
        int i = 0;
        try {
            fin = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }
        String currentVal = "";
        fin.nextInt();
        fin.nextInt();
        int hold;
        while(fin.hasNextLine()) {

                val[i] = fin.nextInt();
                //System.out.println(val[i]);
                hold = fin.nextInt();

            //System.out.println(val[i]);
            i++;
        }

        fin.close();
        return val;
    }
    public static int[] readFileVal(String filename, int[]val) {
        Scanner fin = null;
        int i = 0;
        try {
            fin = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }
        String currentVal = "";
        int hold;
        fin.nextInt();
        fin.nextInt();
        while(fin.hasNextLine()) {
            //if(i!=0) {
                hold = fin.nextInt();
                val[i] = fin.nextInt();
            //}
            //System.out.println(val[i]);
            i++;
        }

        fin.close();
        return val;
    }
}
class Knapsack {

    // A utility function that returns maximum of two integers
    public int max(int a, int b) {
        if(a>b) {
            return a;
        }
        else {
            return b;
        }
    }
    public void printknapSack(int capacity, int weights[], int val[], int size) {
        int i, w;
        int sack[][] = new int[size + 1][capacity + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= size; i++) {
            for (w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    sack[i][w] = 0;
                else if (weights[i - 1] <= w)
                    sack[i][w] = Math.max(val[i - 1] + sack[i - 1][w - weights[i - 1]], sack[i - 1][w]);
                else
                    sack[i][w] = sack[i - 1][w];
            }
        }

        // stores the result of Knapsack
        int result = sack[size][capacity];
        System.out.println("Knapsack: " + result);
        System.out.print("{");
        w = capacity;
        for (i = size; i > 0 && result > 0; i--) {
            if (result == sack[i - 1][w])
                continue;
            else {
                //System.out.print(weights[i - 1] + " " );
                System.out.print(i + " ");
                result = result - val[i - 1];
                w = w - weights[i - 1];
            }
        }
        System.out.println("}");
    }

}
