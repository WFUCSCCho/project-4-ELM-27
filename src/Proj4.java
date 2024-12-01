/*
 * @file: Proj4.java
 * @description: This class runs the tests for SeparateChainingHashTable.java
 * @author: Elliott Lowman
 * @date: November 30, 2024
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);
        SeparateChainingHashTable<Catcher> hashTable1 = new SeparateChainingHashTable<>();
        SeparateChainingHashTable<Catcher> hashTable2 = new SeparateChainingHashTable<>();
        SeparateChainingHashTable<Catcher> hashTable3 = new SeparateChainingHashTable<>();
        ArrayList<Catcher> testList = new ArrayList<>();
        long startTime;
        long endTime;

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        writeToFile(Integer.toString(numLines), "analysis.txt");

        // insert items into testList
        for(int i = 0; i < numLines; i++) {
            testList.add(new Catcher(inputFileNameScanner.nextLine()));
        }

        // tests for sorted list
        Collections.sort(testList);
        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable1.insert(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Sorted Insertion: " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable1.contains(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Sorted Search: " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable1.remove(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Sorted Removal: " + (endTime - startTime) + " nanoseconds.");

        // tests for unsorted list
        Collections.shuffle(testList);
        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable2.insert(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Unsorted Insertion: " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable2.contains(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Unsorted Search: " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable2.remove(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Unsorted Removal: " + (endTime - startTime) + " nanoseconds.");

        // tests for reversed list
        Collections.sort(testList, Collections.reverseOrder());
        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable3.insert(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Reversed Insertion: " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable3.contains(testList.get(i));
        }
        endTime = System.nanoTime();
        writeToFile(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Reversed Search: " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashTable3.remove(testList.get(i));
        }
        endTime = System.nanoTime();
        fileNewLine(Long.toString(endTime - startTime), "analysis.txt");
        System.out.println("Reversed Removal: " + (endTime - startTime) + " nanoseconds.");
    }

    public static void writeToFile(String content, String filePath) throws IOException {
        FileWriter outFile = new FileWriter(filePath, true);  // navigates to end of file

        outFile.write(content + ",");

        outFile.close();
    }

    public static void fileNewLine(String content, String filePath) throws IOException {
        FileWriter outFile = new FileWriter(filePath, true);  // navigates to end of file

        outFile.write(content + "\n");

        outFile.close();
    }
}
