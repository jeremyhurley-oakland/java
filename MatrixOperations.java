import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MatrixOperations {

    public static void main(String[] args) {
        if (args.length == 2) {
            // Two command line arguments provided, assume they are file names
            processFiles(args[0], args[1]);
        } else if (args.length == 1) {
            // One command line argument provided, assume it's an integer
            try {
                int size = Integer.parseInt(args[0]);
                generateAndMultiplyMatrices(size);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please provide two file names or an integer.");
            }
        } else {
            // No command line arguments, use Scanner for user input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter two file names or an integer:");
            if (scanner.hasNextInt()) {
                int size = scanner.nextInt();
                generateAndMultiplyMatrices(size);
            } else if (scanner.hasNext()) {
                String file1 = scanner.next();
                if (scanner.hasNext()) {
                     String file2 = scanner.next();
                     processFiles(file1, file2);
                } else {
                    System.err.println("Please provide two file names.");
                }
            } else {
                System.err.println("Invalid input. Please provide two file names or an integer.");
            }
            scanner.close();
        }
    }

    private static void processFiles(String file1Path, String file2Path) {
        try {
            int[][] matrix1 = readMatrixFromFile(file1Path);
            int[][] matrix2 = readMatrixFromFile(file2Path);

            if (matrix1[0].length != matrix2.length) {
                System.err.println("Matrices dimensions are not compatible for multiplication.");
                return;
            }

            int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);
            saveMatrixToFile("matrix3.txt", resultMatrix);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    private static void generateAndMultiplyMatrices(int size) {
        int[][] matrix1 = generateRandomMatrix(size, size);
        int[][] matrix2 = generateRandomMatrix(size, size);

        try {
            saveMatrixToFile("matrix1.txt", matrix1);
            saveMatrixToFile("matrix2.txt", matrix2);
        } catch (IOException e) {
            System.err.println("Error saving matrices to files: " + e.getMessage());
            return;
        }

        int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);
        try {
            saveMatrixToFile("matrix3.txt", resultMatrix);
        } catch (IOException e) {
             System.err.println("Error saving the result matrix to file: " + e.getMessage());
        }
    }

    private static int[][] readMatrixFromFile(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));
        int rows = 0;
        int cols = 0;
        //Determine the rows and cols
        while (scanner.hasNextLine()) {
            String[] elements = scanner.nextLine().split(" ");
            cols = elements.length;
            rows++;
        }
        scanner.close();

       
        scanner = new Scanner(new File(filePath));
        int[][] matrix = new int[rows][cols];
        int row = 0;
        while (scanner.hasNextLine()) {
            String[] elements = scanner.nextLine().split(" ");
             for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(elements[col]);
            }
            row++;
        }
        scanner.close();
        return matrix;
    }

    private static int[][] generateRandomMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10); // Random integers between 0 and 9
            }
        }
        return matrix;
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        int[][] resultMatrix = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return resultMatrix;
    }

    private static void saveMatrixToFile(String filePath, int[][] matrix) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                writer.write(row[i] + (i == row.length - 1 ? "" : " "));
            }
            writer.write(System.lineSeparator());
        }
        writer.close();
    }
}