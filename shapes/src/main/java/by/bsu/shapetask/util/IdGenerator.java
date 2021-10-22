package by.bsu.shapetask.util;

import by.bsu.shapetask.warehouse.Warehouse;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IdGenerator {
    private static IdGenerator instance;
    public UUID generateId() {
        Warehouse warehouse = Warehouse.getInstance();
        Set<UUID> idStorage = new HashSet<>(warehouse.getKeys());
        int currentSize = idStorage.size();
        UUID id;
        do {
            id = UUID.randomUUID();
            idStorage.add(id);
        } while (currentSize == idStorage.size());
        return id;
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    private IdGenerator() {}
}
