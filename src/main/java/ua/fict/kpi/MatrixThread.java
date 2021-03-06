package ua.fict.kpi;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MatrixThread extends Thread {
    private int i;
    private int j;
    private int[] row;
    private int[] column;
    private int[][] resultMatrix;

    MatrixThread (int i, int[] row, int[][] resultMatrix) {
        this.i = i;
        this.row = row;
        this.resultMatrix = resultMatrix;
    }

    @Override
    public void run() {
        int result = 0;

        for (int i = 0; i < row.length; i++) {
            result += row[i] * column[i];
        }

        resultMatrix[i][j] = result;
    }
}
