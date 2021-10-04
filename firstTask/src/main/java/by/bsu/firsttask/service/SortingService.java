package by.bsu.firsttask.service;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;

public interface SortingService {
    void bubbleSort(CustomArray array) throws CustomArrayException;
    void insertionSort(CustomArray array) throws CustomArrayException;
    void selectionSort(CustomArray array) throws CustomArrayException;
}
