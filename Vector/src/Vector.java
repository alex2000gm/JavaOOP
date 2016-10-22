
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

    // TODO: 22.10.16 сделать конструктор копирования
    public Vector(Vector vector) {
        this.vectorLength = vector.getVectorLength();
        this.vector = new double[vector.getVectorLength()];
        for (int i = 0; i < vector.getVectorLength(); i++) {
            this.vector[i] = vector.getVector()[i];
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

    public int getVectorLength() {
        return vectorLength;
    }

    // TODO: 22.10.16
    @Override
    public String toString() {
        StringBuilder printVector = new StringBuilder();
        int counter = 0;
        while (counter < this.getVectorLength() - 1) {
            printVector.append(this.getVector()[counter]);
            printVector.append(", ");
            counter++;
        }
        printVector.append(this.getVector()[counter]);
        printVector.append(";");
        return printVector.toString();
    }
}
