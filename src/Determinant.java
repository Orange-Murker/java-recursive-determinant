/**
 * Simple Java class to compute determinants
 * @author Orange_Murker
 */

import java.util.Arrays;

public class Determinant {
    /**
     * Computes a determinant of a matrix by performing cofactor expansion on the first row.
     * @param matrix a matrix in [row][col] form that will be used to compute the determinant
     * @return the determinant
     */
    //@requires (\forall int i; i < matrix.length; matrix.length == matrix[i].length);
    public int determinant(int[][] matrix) {
        // Return the determinant if we can
        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int result = 0;
        for (int factor = 0; factor < matrix.length; factor++) {
            // Compute the minor for this factor number
            int[][] minor = new int[matrix.length - 1][matrix.length - 1];
            for (int col = 0; col < matrix.length; col++) {
                // We are not interested in row 0 since it was eliminated
                for (int row = 1; row < matrix.length; row++) {
                    if (col != factor) {
                        int minorcol = col;
                        // Use col - 1 instead if we already passed by it
                        if (col > factor) {
                            minorcol = col - 1;
                        }
                        minor[row - 1][minorcol] = matrix[row][col];
                    }
                }
            }
            // Sign * first row element * determinant
            result += (factor % 2 == 0 ? 1 : -1) * matrix[0][factor] * determinant(minor);
        }
        return result;
    }

    /**
     * Construct a string to represent a matrix.
     * @param matrix a matrix to show in [row][col] form
     * @return a string representing the matrix
     */
    public String prettyMatrix(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        String formatter = "|" + "%3s ".repeat(matrix[0].length) + "|%n";
        for (int[] row : matrix) {
            Integer[] rowObj = new Integer[row.length];
            Arrays.setAll(rowObj, i -> row[i]);
            stringBuilder.append(String.format(formatter, rowObj));
        }
        return stringBuilder.toString();
    }

    /**
     * Sample.
     * Compute a determinant of a 4 by 4 matrix
     * @param args n/a
     */
    public static void main(String[] args) {
        int[][] matrix = {{1,5,31,0}, {32,3,123,3}, {1,2,4,9}, {4,7,1,8}};

        Determinant determinant = new Determinant();

        System.out.println();
        System.out.println(determinant.prettyMatrix(matrix));
        System.out.println("Determinant: " + determinant.determinant(matrix));
    }
}