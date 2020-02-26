package ua.fict.kpi;

public class MatrixService {
    private int first[][] = new int[150][150];
    private int second[][] = new int[150][150];
    private int result[][] = new int[150][150];

    private double sec = 0;

    public void startAlgo(){
        initializeMatrix();
        long start = System.currentTimeMillis();
        algo();
        long finish = System.currentTimeMillis();

        sec = (double)(finish - start)/1000;

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
        for (int i = 0; i < first.length; i++){
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    int[] row = first[finalI];
                    int res = 0;
                    for (int j = 0; j < row.length; j++) {
                        for (int k = 0; k < row.length; k++) {
                            res += row[k] * second[k][j];
                        }
                        result[finalI][j] = res;
                        res = 0;
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
}
