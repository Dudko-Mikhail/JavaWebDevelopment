package by.bsu.task6.runner;

import by.bsu.task6.creator.TrainGenerator;
import by.bsu.task6.entity.train.Train;
import by.bsu.task6.exception.ReaderException;
import by.bsu.task6.parser.impl.CustomParserImpl;
import by.bsu.task6.reader.impl.CustomFileReaderImpl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ReaderException {
        CustomFileReaderImpl fileReader = new CustomFileReaderImpl();
        List<String> trainsData = fileReader.readLines("src/main/resources/data/trains.txt");
        List<Train> trains = new CustomParserImpl().parseTrains(trainsData);

//        List<Train> trains = new ArrayList<>();
//        TrainGenerator generator = TrainGenerator.getInstance();
//        for (int i = 0; i < 10; i++) {
//            trains.add(generator.getRandomTrain());
//        }

        ExecutorService service = Executors.newFixedThreadPool(10);
        trains.forEach(service::execute);
        service.shutdown();
    }
}