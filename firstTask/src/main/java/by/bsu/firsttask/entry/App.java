package by.bsu.firsttask.entry;

import by.bsu.firsttask.exception.CustomArrayException;
import by.bsu.firsttask.exception.CustomReaderException;
import by.bsu.firsttask.reader.CustomFileReader;
import by.bsu.firsttask.reader.impl.CustomFileReaderImpl;

public class App {
    public static void main(String[] args) { // TODO подумать над swap
        // TODO fileReader тесты
        // TODO service тесты
        CustomFileReader reader = new CustomFileReaderImpl();
        try {
            reader.readLines("src/main/resources/data/data.txt");
        } catch (CustomReaderException e) {
            System.out.println("gabella");
            e.printStackTrace();
        }

//        int[] copy = {-8, 3, 8, 4, 7, 4, 2, 96, 1};
//        CustomArray array = new CustomArray(copy);
//        CalculationService calculationService = new CalculationServiceImpl();
//        try {
//            System.out.println(calculationService.findAverage(array));
//        } catch (CalculationServiceException e) {
//            e.printStackTrace();
//        }
//        System.out.println("before sort: " + array);
//        SortingService sortingService = new SortingServiceImpl();
//        sortingService.selectionSort(array);
//        System.out.println("after sort: " + array);

    }
}
