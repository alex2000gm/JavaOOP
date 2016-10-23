import java.util.Arrays;

/**
 * Created by alex2000 on 22.10.16.
 */
public class Vector {
    private double[] vector;
    private int vectorLength;

    public Vector(int vectorLength) {
        this.vectorLength = vectorLength;
        this.vector = new double[vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            this.vector[i] = 0;
        }
    }

    public Vector(double[] array) {
        this.vectorLength = array.length;
        this.vector = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            this.vector[i] = array[i];
        }
    }

    public Vector(Vector vectorForCopy) {
        this.vectorLength = vectorForCopy.getSize();
        this.vector = new double[vectorForCopy.getSize()];
        for (int i = 0; i < vectorForCopy.getSize(); i++) {
            this.vector[i] = vectorForCopy.getVector()[i];
        }
    }

    public Vector(int vectorLength, double[] array) {
        this.vectorLength = vectorLength;
        this.vector = new double[vectorLength];
        if (vectorLength == array.length) {
            for (int i = 0; i < vectorLength; i++) {
                this.vector[i] = array[i];
            }
        } else {
            int counter = 0;
            while (vectorLength <= array.length) {
                this.vector[counter] = array[counter];
            }
            while (counter < vectorLength) {
                this.vector[counter] = 0;
            }
        }
    }

    public double[] getVector() {
        return vector;
    }

    public int getSize() {
        return vectorLength;
    }

    public double getNumByIndex(int index) {
        return this.getVector()[index];
    }

    public void setNumByIndex(int index, double newValue) {
        this.getVector()[index] = newValue;
    }

    @Override
    public String toString() {
        StringBuilder printVector = new StringBuilder();
        int counter = 0;
        while (counter < this.getSize()) {
            printVector.append(this.getVector()[counter]);
            printVector.append(", ");
            counter++;
        }
        printVector.append("Size = ");
        printVector.append(this.getSize());
        printVector.append(";");
        return printVector.toString();
    }

    private Vector findBiggerVector(Vector vector1, Vector vector2) {
        if (vector1.getSize() > vector2.getSize()) {
            return vector1;
        } else return vector2;
    }

    private Vector findLesserVector(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.getSize()) {
            return vector1;
        } else return vector2;
    }

    public Vector sumOfVectors(Vector vectorForSum) {
        Vector sumOfVectors = new Vector(Math.max(this.getSize(), vectorForSum.getSize()));
        int index = 0;
        if (this.getSize() != vectorForSum.getSize()) {
            while (index < Math.min(this.getSize(), vectorForSum.getSize())) {
                sumOfVectors.getVector()[index] = this.getVector()[index] + vectorForSum.getVector()[index];
                index++;
            }
            System.arraycopy(findBiggerVector(this, vectorForSum).getVector(), index, sumOfVectors.getVector(), index, vectorForSum.getSize() - index);
        } else {
            for (index = 0; index < sumOfVectors.getSize(); index++)
                sumOfVectors.getVector()[index] = this.getVector()[index] + vectorForSum.getVector()[index];
        }
        return sumOfVectors;
    }

    public Vector differenceOfVectors(Vector vectorForDifference) {
        Vector differenceOfVectors = new Vector(Math.max(this.getSize(), vectorForDifference.getSize()));
        int index = 0;
        if (this.getSize() != vectorForDifference.getSize()) {
            while (index < Math.min(this.getSize(), vectorForDifference.getSize())) {
                differenceOfVectors.getVector()[index] = this.getVector()[index] - vectorForDifference.getVector()[index];
                index++;
            }
            if (this.getSize() > vectorForDifference.getSize()) {
                System.arraycopy(this.getVector(), index, differenceOfVectors.getVector(), index, this.getSize() - index);
            } else {
                while (index < differenceOfVectors.getSize()) {
                    differenceOfVectors.getVector()[index] = findBiggerVector(this, vectorForDifference).getVector()[index] * (-1);
                    index++;
                }
            }
        } else {
            for (int i = 0; i < differenceOfVectors.getSize(); i++) {
                differenceOfVectors.getVector()[i] = this.getVector()[i] - vectorForDifference.getVector()[i];
            }
        }
        return differenceOfVectors;
    }

    public Vector getMultiply(double multiplier) {
        Vector multipliedVector = new Vector(this.getSize());
        for (int i = 0; i < multipliedVector.getSize(); i++) {
            multipliedVector.getVector()[i] = this.getVector()[i] * multiplier;
        }
        return multipliedVector;
    }

    public Vector getInverse() {
        Vector inversedVector = new Vector(this.getSize());
        for (int i = 0; i < inversedVector.getSize(); i++) {
            inversedVector.getVector()[i] = this.getVector()[i] * (-1);
        }
        return inversedVector;
    }

    public static Vector sumOfVectors(Vector vector1, Vector vector2) {
        Vector sumOfVectors = new Vector(Math.max(vector1.getSize(), vector2.getSize()));
        int index = 0;
        if (vector1.getSize() != vector2.getSize()) {
            while (index < Math.min(vector1.getSize(), vector2.getSize())) {
                sumOfVectors.getVector()[index] = vector1.getVector()[index] + vector2.getVector()[index];
                index++;
            }
            Vector biggerVector = vector1;
            if (vector1.getSize() > vector2.getSize()) {
                System.arraycopy(biggerVector.getVector(), index, sumOfVectors.getVector(), index, sumOfVectors.getSize() - index);
            } else {
                biggerVector = vector2;
                System.arraycopy(biggerVector.getVector(), index, sumOfVectors.getVector(), index, sumOfVectors.getSize() - index);
            }
        } else {
            for (index = 0; index < sumOfVectors.getSize(); index++)
                sumOfVectors.getVector()[index] = vector1.getVector()[index] + vector2.getVector()[index];
        }
        return sumOfVectors;
    }

    public static Vector differenceOfVectors(Vector vector1, Vector vector2) {
        Vector differenceOfVectors = new Vector(Math.max(vector1.getSize(), vector2.getSize()));
        int index = 0;
        if (vector1.getSize() != vector2.getSize()) {
            while (index < Math.min(vector1.getSize(), vector2.getSize())) {
                differenceOfVectors.getVector()[index] = vector1.getVector()[index] - vector2.getVector()[index];
                index++;
            }
            if (vector1.getSize() > vector2.getSize()) {
                System.arraycopy(vector1.getVector(), index, differenceOfVectors.getVector(), index, vector1.getSize() - index);
            } else {
                System.arraycopy(vector2.getVector(), index, differenceOfVectors.getVector(), index, vector2.getSize() - index);
                while (index < differenceOfVectors.getSize()) {
                    differenceOfVectors.getVector()[index] = differenceOfVectors.getVector()[index] * (-1);
                    index++;
                }
            }
        } else {
            for (int i = 0; i < differenceOfVectors.getSize(); i++) {
                differenceOfVectors.getVector()[i] = vector1.getVector()[i] - vector2.getVector()[i];
            }
        }
        return differenceOfVectors;
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        double scalarMultiplication = 0;
        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); i++) {
            scalarMultiplication = scalarMultiplication + vector1.getVector()[i] * vector2.getVector()[i];
        }
        return scalarMultiplication;
    }

    public boolean equals(Vector vector) {
        boolean resultOfCompare = true;
        if (this.getSize() != vector.getSize()) {
            resultOfCompare = false;
        }
        int index = 0;
        while (resultOfCompare && index < vector.getSize()) {
            resultOfCompare = isComparison(this.getVector()[index], vector.getVector()[index]);
            index++;
        }
        return resultOfCompare;
    }

    private boolean isComparison(double numForCompare1, double numForCompare2) {
        final double EPSILON = 0.0001;
        return Math.abs(numForCompare1 - numForCompare2) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector1 = (Vector) o;

        if (vectorLength != vector1.vectorLength) return false;
        return Arrays.equals(getVector(), vector1.getVector());

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(getVector());
        result = 31 * result + vectorLength;
        return result;
    }
}
