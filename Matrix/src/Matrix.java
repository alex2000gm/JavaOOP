
public class Matrix {
    private int horizontalSize;
    private int verticalSize;
    private Vector[] matrix;

    public Matrix(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        this.matrix = new Vector[verticalSize];
        for (int i = 0; i < verticalSize; i++) {
            matrix[i] = new Vector(horizontalSize);
        }
    }

    public Matrix(Vector... vectors) {
        this.verticalSize = vectors.length;
        this.matrix = new Vector[verticalSize];
        int maxSize = 0;
        for (int i = 0; i < verticalSize; i++) {
            if (vectors[i].getSize() > maxSize) {
                maxSize = vectors[i].getSize();
            }
        }
        this.horizontalSize = maxSize;
        for (int i = 0; i < verticalSize; i++) {
            if (vectors[i].getSize() < maxSize) {
                double[] valueForConstructor = vectors[i].getVector();
                this.matrix[i] = new Vector(maxSize, valueForConstructor);
            } else {
                this.matrix[i] = new Vector(vectors[i]);
            }
        }
    }

    public Matrix(double[][] arrays) {
        int maxArrayLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > maxArrayLength) {
                maxArrayLength = arrays[i].length;
            }
            this.horizontalSize = arrays.length;
            this.verticalSize = maxArrayLength;
        }
        for (int i = 0; i < arrays.length; i++) {
            this.matrix[i] = new Vector(maxArrayLength, arrays[i]);
        }
    }

    public Matrix(Matrix matrixForCopy) {
        Matrix copyOfMatrix = new Matrix(matrixForCopy.getHorizontalSize(), matrixForCopy.getVerticalSize());
        for (int i = 0; i < horizontalSize; i++) {
            copyOfMatrix.getMatrix()[i] = new Vector(matrixForCopy.getMatrix()[i]);
        }
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public Vector[] getMatrix() {
        return matrix;
    }

    public Vector getVectorByIndex(int index) {
        return this.getMatrix()[index];
    }

    public Vector getColumnVector(int indexForSearch) {
        Vector column = new Vector(this.getVerticalSize());
        for (int i = 0; i < this.getVerticalSize(); i++) {
            column.setNumByIndex(i, getVectorByIndex(i).getNumByIndex(indexForSearch));
        }
        return column;
    }

    public Matrix getTransposedMatrix(Matrix matrix) {
        Vector[] columnVectors = new Vector[matrix.getHorizontalSize()];
        for (int i = 0; i < matrix.getHorizontalSize(); i++) {
            columnVectors[i] = matrix.getColumnVector(i);
        }
        return new Matrix(columnVectors);
    }

    public Matrix getMultiplied(double scalar) {
        Vector[] multipliedVectors = new Vector[this.getVerticalSize()];
        for (int i = 0; i < this.getVerticalSize(); i++) {
            multipliedVectors[i] = this.getVectorByIndex(i).getMultiplied(scalar);
        }
        return new Matrix(multipliedVectors);
    }

    public Matrix getMultiplied(Vector vector) {
        Matrix multiplication = new Matrix(1, this.getVerticalSize());
        for (int i = 0; i < this.getVerticalSize(); i++) {
            double[] resultOfMultiply = {Vector.getMultiplied(this.getVectorByIndex(i), vector)};
            multiplication.setVectorByIndex(i, new Vector(resultOfMultiply));
        }
        return multiplication;
    }

    public Matrix getSumOfMatrixes(Matrix matrix) {
        Matrix sumOfMatrixes = new Matrix(Math.max(this.getHorizontalSize(), matrix.getHorizontalSize()), Math.max(this.getVerticalSize(), matrix.getVerticalSize()));
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            sumOfMatrixes.setVectorByIndex(i, this.getVectorByIndex(i).sumOfVectors(matrix.getVectorByIndex(i)));
        }
        return sumOfMatrixes;
    }

    public Matrix getDifferenceOfMatrixes(Matrix matrix) {
        Matrix differenceOfMatrixes = new Matrix(Math.max(this.getHorizontalSize(), matrix.getHorizontalSize()), Math.max(this.getVerticalSize(), matrix.getVerticalSize()));
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            differenceOfMatrixes.setVectorByIndex(i, this.getVectorByIndex(i).differenceOfVectors(matrix.getVectorByIndex(i)));
        }
        return differenceOfMatrixes;
    }

    public static Matrix sumOfMatrixes(Matrix matrix1, Matrix matrix2) {
        Matrix sumOfMatrixes = new Matrix(Math.max(matrix1.getHorizontalSize(), matrix2.getHorizontalSize()), Math.max(matrix1.getVerticalSize(), matrix2.getVerticalSize()));
        for (int i = 0; i < sumOfMatrixes.getVerticalSize(); i++) {
            if (i < Math.min(matrix1.getVerticalSize(), matrix2.getVerticalSize())) {
                sumOfMatrixes.setVectorByIndex(i, matrix1.getVectorByIndex(i).sumOfVectors(matrix2.getVectorByIndex(i)));
            } else {
                if (matrix1.getVerticalSize() > matrix2.getVerticalSize()) {
                    sumOfMatrixes.setVectorByIndex(i, matrix1.getVectorByIndex(i));
                } else {
                    sumOfMatrixes.setVectorByIndex(i, matrix2.getVectorByIndex(i));
                }
            }
        }
        return sumOfMatrixes;
    }

    public static Matrix differenceOfMatrixes(Matrix matrix1, Matrix matrix2) {
        Matrix difference = new Matrix(Math.max(matrix1.getHorizontalSize(), matrix2.getHorizontalSize()), Math.max(matrix1.getVerticalSize(), matrix2.getVerticalSize()));
        for (int i = 0; i < difference.getVerticalSize(); i++) {
            if (i < Math.min(matrix1.getVerticalSize(), matrix2.getVerticalSize())) {
                difference.setVectorByIndex(i, matrix1.getVectorByIndex(i).differenceOfVectors(matrix2.getVectorByIndex(i)));
            } else {
                if (matrix1.getVerticalSize() > matrix2.getVerticalSize()) {
                    difference.setVectorByIndex(i, matrix1.getVectorByIndex(i));
                } else {
                    difference.setVectorByIndex(i, matrix2.getVectorByIndex(i).getInverse());
                }
            }
        }
        return difference;
    }

    public static Matrix getMultiplied(Matrix matrix1, Matrix matrix2) {
        Matrix multiplication = new Matrix(matrix2.getHorizontalSize(), matrix1.getVerticalSize());
        for (int i = 0; i < multiplication.getVerticalSize(); i++) {
            Vector rowOfResults = new Vector(multiplication.getHorizontalSize());
            for (int j = 0; j < multiplication.getHorizontalSize(); j++) {
                double result = Vector.getMultiplied(matrix1.getVectorByIndex(i), matrix2.getColumnVector(j));//double значение произведения
                rowOfResults.setNumByIndex(j, result);
            }
            multiplication.setVectorByIndex(i, rowOfResults);
        }
        return multiplication;
    }

    public void setVectorByIndex(int index, Vector vector) {
        this.getMatrix()[index] = new Vector(vector);
    }

    @Override
    public String toString() {
        StringBuilder printMatrix = new StringBuilder();
        printMatrix.append("{");
        int index = 0;
        while (index < this.getVerticalSize()) {
            printMatrix.append("{");
            printMatrix.append(this.getVectorByIndex(index).toString());
            if (index < this.getVerticalSize() - 1) {
                printMatrix.append("}, ");
            } else {
                printMatrix.append("}");
            }
            index++;
        }
        printMatrix.append("}");
        return printMatrix.toString();
    }
}