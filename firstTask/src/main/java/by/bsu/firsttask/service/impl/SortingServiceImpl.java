package by.bsu.firsttask.service.impl;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import by.bsu.firsttask.service.SortingService;

public class SortingServiceImpl implements SortingService {
    @Override
    public void bubbleSort(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("Cannot sort array: array is null");
        }
        for (int i = 0; i < array.length() - 1; i++) {
            for (int j = 0; j < array.length() - i - 1; j++) {
                if (array.getElement(j) > array.getElement(j + 1)) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    @Override
    public void insertionSort(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("Cannot sort array: array is null");
        }
        for (int i = 1; i < array.length(); i++) {
            int value = array.getElement(i);
            int j = i - 1;
            while (j >= 0 && value < array.getElement(j)) {
                array.setElement(j + 1, array.getElement(j));
                j--;
            }
            array.setElement(j + 1, value);
        }
    }

    @Override
    public void selectionSort(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("Cannot sort array: array is null");
        }
        int minIndex;
        for (int i = 0; i < array.length(); i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length(); j++) {
                int currentNumber = array.getElement(j);
                if (currentNumber < array.getElement(minIndex)) {
                    minIndex = j;
                }
            }
            if (array.getElement(i) != array.getElement(minIndex)) {
                swap(array, i, minIndex);
            }
        }
    }

    private void swap(CustomArray array, int i, int j) {
        int element = array.getElement(i);
        array.setElement(i, array.getElement(j));
        array.setElement(j, element);
    }
}
