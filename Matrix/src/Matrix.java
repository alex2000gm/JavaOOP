import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

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

    public double getDeterminant() {
        double determinant = 0;
        int numOfCombinations = 1;
        for (int i = 1; i <= this.getHorizontalSize(); i++) {
            numOfCombinations = numOfCombinations * i;
        }

        ArrayList<Matrix> combinations = new ArrayList<>();

        while (combinations.size() < numOfCombinations) {


            for (int i = 0; i < this.getHorizontalSize(); i++) {
                for (int j = 0; j < this.getVerticalSize(); j++) {
// TODO: 16.11.16  нужен цикл для повторных результатов для каждого множителя
                    for (int k = 0; k < this.getHorizontalSize() - 1; k++) {
                        Vector firstMultiplier = new Vector(i, j, this.getVectorByIndex(i).getNumByIndex(j));
                        boolean isNew = true;
                        for (int c = 0; c < combinations.size(); c++) {
                            if (getIndexesCompare(combinations.get(c), getCombination(firstMultiplier, this, combinations, k))) {
                                isNew = false;
                            }
                        }
                        if (isNew) {
                            combinations.add(getCombination(firstMultiplier, this, combinations, k));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < combinations.size(); i++) {
            double memberOfSum = 1;

            combinations.get(i).getColumnVector(2).getVector();
            for (int j = 0; j < combinations.get(i).getColumnVector(2).getSize(); j++) {
                memberOfSum = combinations.get(i).getColumnVector(2).getNumByIndex(j) * memberOfSum;
            }
            determinant = getSignByParity(combinations.get(i)) * memberOfSum + determinant;
        }
        return determinant;
    }


    private static Matrix getCombination(Vector firstMultiplier, Matrix matrixForSearch, ArrayList<Matrix> combinations, int k) {

        Matrix combination = new Matrix(3, matrixForSearch.getHorizontalSize());
        combination.setVectorByIndex(0, firstMultiplier);
        ArrayList<Vector> usedInRow = new ArrayList<>();
        ArrayList<Vector> usedInColumn = new ArrayList<>();
        usedInRow.add(new Vector((double) firstMultiplier.getNumByIndex(0)));
        usedInColumn.add(new Vector((double) firstMultiplier.getNumByIndex(1)));

        int rowIndex = (int) firstMultiplier.getNumByIndex(0);
        int columnIndex = (int) firstMultiplier.getNumByIndex(1);

        for (int i = 1; i < combination.getVerticalSize(); i++) {
            boolean rowIndexFound = false;
            while (!rowIndexFound) {
                if (rowIndex == matrixForSearch.getVerticalSize() - 1) {
                    if (usedInRow.contains(new Vector((double) rowIndex))) {
                        rowIndex = 0;
                    } else {
                        rowIndexFound = true;
                        usedInRow.add(new Vector((double) rowIndex));
                    }
                } else if (usedInRow.contains(new Vector((double) rowIndex))) {
                    rowIndex++;

                } else if (usedInRow.size() == matrixForSearch.getVerticalSize()) {
                    break;
                } else {
                    rowIndexFound = true;
                    usedInRow.add(new Vector((double) rowIndex));
                }
            }

            boolean columnIndexFound = false;
            while (!columnIndexFound) {
                if (columnIndex == matrixForSearch.getHorizontalSize() - 1) {
                    if (usedInColumn.contains(new Vector((double) columnIndex))) {
                        columnIndex = 0;
                    } else {
                        columnIndexFound = true;
                        if (i == 1) {
                            if (columnIndex == (matrixForSearch.getHorizontalSize() - 1)) {
                                if (k == 1) {
                                    columnIndex = 0;
                                }
                            } else if ((columnIndex + k) > matrixForSearch.getHorizontalSize() - 1) {
                                columnIndex = columnIndex + k - matrixForSearch.getHorizontalSize() - 1;
                            } else {
                                columnIndex = columnIndex + k;
                            }
                        }
                        usedInColumn.add(new Vector((double) columnIndex));
                    }
                } else if (usedInColumn.contains(new Vector((double) columnIndex))) {
                    columnIndex++;
                } else if (usedInColumn.size() == matrixForSearch.getHorizontalSize()) {
                    break;
                } else {
                    columnIndexFound = true;
                    if (i == 1) {
                        if (columnIndex == (matrixForSearch.getHorizontalSize() - 1)) {
                            columnIndex = 0;
                        } else if ((columnIndex + k) > matrixForSearch.getHorizontalSize() - 1) {
                            columnIndex = columnIndex + k - matrixForSearch.getHorizontalSize() - 1;
                        } else {
                            columnIndex = columnIndex + k;
                        }
                    }
                    usedInColumn.add(new Vector((double) columnIndex));
                }
            }
            Vector combinationMember = new Vector(rowIndex, columnIndex, matrixForSearch.getVectorByIndex(rowIndex).getNumByIndex(columnIndex));
            combination.setVectorByIndex(i, combinationMember);
        }

        return getSortByRowNum(combination);
    }

    private static Matrix getSortByRowNum(Matrix matrixForSort) {
        Matrix sorted = new Matrix(matrixForSort.getHorizontalSize(), matrixForSort.getVerticalSize());

        for (int i = 0; i < matrixForSort.getVerticalSize(); i++) {
            for (int j = 0; j < matrixForSort.getVerticalSize(); j++) {
                if ((int) matrixForSort.getVectorByIndex(j).getNumByIndex(0) == i) {
                    sorted.setVectorByIndex(i, matrixForSort.getVectorByIndex(j));
                }
            }
        }
        return sorted;
    }

    private static int getSignByParity(Matrix multiplier) {
        int parity = 0;
        for (int i = 0; i < multiplier.getVerticalSize(); i++) {
            for (int j = 0; j < i; j++) {
                if (multiplier.getVectorByIndex(i).getNumByIndex(1) < multiplier.getVectorByIndex(j).getNumByIndex(1)) {
                    parity = parity + 1;
                }
            }
        }
        if (parity % 2 == 0) {
            return 1;
        }
        return -1;
    }

    private static boolean getIndexesCompare(Matrix matrix1, Matrix matrix2) {

        if (matrix1.getVerticalSize() != matrix2.getVerticalSize() || matrix1.getHorizontalSize() != matrix2.getHorizontalSize()) {
            return false;
        }
        for (int i = 0; i < matrix1.getVerticalSize(); i++) {
            for (int j = 0; j < 2; j++) {

                if ((int) matrix1.getVectorByIndex(i).getNumByIndex(j) != (int) matrix2.getVectorByIndex(i).getNumByIndex(j)) {
                    return false;
                }
            }
        }
        return true;
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

    public void setColumnVector(int indexForSearch, Vector vector) {
        for (int i = 0; i < this.getVerticalSize(); i++) {
            this.getVectorByIndex(i).setNumByIndex(indexForSearch, vector.getNumByIndex(i));
        }
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

    private static double getMultiplier(int index1, int index2, Vector vector) {
        return vector.getNumByIndex(index2) / vector.getNumByIndex(index1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;

        Matrix matrix1 = (Matrix) o;

        if (getHorizontalSize() != matrix1.getHorizontalSize()) return false;
        if (getVerticalSize() != matrix1.getVerticalSize()) return false;

        for (int i = 0; i < this.getVerticalSize(); i++) {
            if (!Arrays.equals(this.getVectorByIndex(i).getVector(), matrix1.getVectorByIndex(i).getVector())) {
                return false;
            }
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        //return Arrays.equals(getMatrix(), matrix1.getMatrix());
        return true;
    }

    @Override
    public int hashCode() {
        int result = getHorizontalSize();
        result = 31 * result + getVerticalSize();
        result = 31 * result + Arrays.hashCode(getMatrix());
        return result;
    }
}