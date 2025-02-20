// All descriptions of the imported java moduals are from https://docs.oracle.com/en/java/javase/23/docs/api/java.base/module-summary.html
// A simple text scanner which can parse primitive types and strings using regular expressions.
import java.util.Scanner;
// An abstract representation of file and directory pathnames.
import java.io.File;
// Writes text to character files using a default buffer size.
import java.io.FileWriter;
// Signals that an I/O exception of some sort has occurred.
import java.io.IOException;
// This package contains classes and interfaces that support a generic API for random number generation.
import java.util.Random;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            
        try {    
            if (args.length == 2) {
                // Two command line arguments provided, assume they are file names
                processFiles(args[0], args[1]);
            } else if (args.length == 1) {
                // One command line argument provided, assume it's an integer
                int size = Integer.parseInt(args[0]);
                generateAndMultiplyMatrices(size);
            } else {
                // use Scanner for user input or command lines
                System.out.println("Enter two file names or an integer:");
                String input = scanner.nextLine();
                String[] parts = input.split(" ");

                if (parts.length == 2) {
                    // Two file names 
                    processFiles(parts[0], parts[1]);
                } else if (parts.length == 1) {
                    try {
                        // One integer
                        int size = Integer.parseInt(parts[0]);
                        generateAndMultiplyMatrices(size);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input. Please provide two file names or an integer.");
                    }
                } else {
                    System.err.println("Invalid input. Please provide two file names or an integer.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    public static void processFiles(String file1Path, String file2Path) {
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

    // Method to generate and multiplya a random matrices
    public static void generateAndMultiplyMatrices(int size) {
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

    // Method to read matrix from a file
    public static int[][] readMatrixFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("File not found: " + filename);
            return null;
        }

        try(Scanner fileScanner = new Scanner(file)) {
            int rows = 0;
            int cols = 0;
            //Determine the number of rows and colums
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] elements = line.split(" ");
                if (rows == 0) {
                    cols = elements.length;
                } else if (cols != elements.length) {
                    System.err.println("Matrix in " + filename + " is not well-formed.");
                    return null;
                }
                rows++;
            }
       
            int[][] matrix = new int[rows][cols];
            Scanner fileScanner2 = new Scanner(file);
            for (int i = 0; i < rows; i++) {
                String line = fileScanner2.nextLine();
                String[] elements = line.split(" ");
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = Integer.parseInt(elements[j]);
                }
            }
            fileScanner2.close();
            return matrix;
        }
    }

    // Method of generating a random matrix
    public static int[][] generateRandomMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10); // Outcome is that it will generates random integers
            }
        }
        return matrix;
    }
    
    // Method of multiply the matrices.
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        if (cols1 != matrix2.length) {
            System.err.println("Matrices cannot be multiplied due to incompatable dimensions.");
            return new int[0][0]; // Outcome is that it will return an empty matrix
        }

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

    // Method of writing the matrix to a file
    public static void saveMatrixToFile(String filePath, int[][] matrix) throws IOException {
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
