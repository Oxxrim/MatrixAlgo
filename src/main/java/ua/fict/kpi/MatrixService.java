package ua.fict.kpi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixService {
    int size = 2000;
    private int first[][] = new int[size][size];
    private int second[][] = new int[size][size];
    private int result[][] = new int[size][size];

    private List<MatrixThread> threads = new ArrayList<>();

    private double sec = 0;

    public void startAlgo(){
        initializeMatrix();
        long start = System.currentTimeMillis();
        algo();
        long finish = System.currentTimeMillis();

        sec = (double)(finish - start)/1000;

        System.out.println(sec);
        printMatrix();
    }

    private void initializeMatrix(){
        for (int i = 0; i < first.length; i++){
            for (int j = 0; j < second.length; j++){
                first[i][j] = 1;
                second[i][j] = 1;
            }
        }
    }

    private void algo(){
        for (int i = 0; i < first.length; i++) {
            int[] row = first[i];

            MatrixThread thread = new MatrixThread(i, row, result);
            threads.add(thread);
        }

        for (int i = 0; i < size; i++) {
            int[] column = getColumn(second, i);

            for (MatrixThread thread : threads) {
                thread.setColumn(column);
                thread.setJ(i);

                thread.run();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printMatrix(){
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println(sec);
    }

    private int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
    }
}
