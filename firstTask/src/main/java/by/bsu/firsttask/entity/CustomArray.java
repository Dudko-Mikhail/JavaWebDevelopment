package by.bsu.firsttask.entity;

import by.bsu.firsttask.exception.CustomArrayException;

import java.util.Arrays;
import java.util.StringJoiner;

public class CustomArray implements Cloneable {
    private int[] array;

    public CustomArray(int length) {
        array = new int[length];
    }

    public CustomArray(int...numbers) {
        this.array = Arrays.copyOf(numbers, numbers.length);
    }

    public int length() {
        return array.length;
    }

    public int getElement(int index) {
//        if (index < 0 || index > (array.length - 1)) {
//            throw new CustomArrayException("index " + index + " out of bounds for length " + array.length);
//        }
        return array[index];
    }

    public void setElement(int index, int element) {
//        if (index < 0 || index > (array.length - 1)) {
//            throw new CustomArrayException("index " + index + " out of bounds for length " + array.length);
//        }
        array[index] = element;
    }

    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }

    public void setArray(int[] array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("Cannot set array: array is null");
        }
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public CustomArray clone() {
        CustomArray clone = null;
        try {
            clone = (CustomArray) super.clone();
            clone.setArray(array);
        } catch (CloneNotSupportedException | CustomArrayException e) { // выброс невозможен
            e.printStackTrace();
        }
        return clone;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < array.length; i++) {
            joiner.add(Integer.toString(array[i]));
        }
        return joiner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomArray array1 = (CustomArray) o;

        return Arrays.equals(array, array1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
