package by.bsu.shapetask.warehouse;

import java.util.*;

public class Warehouse {
    private static Warehouse instance;
    private final Map<UUID, TriangleParameters> map;

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public Optional<TriangleParameters> getTriangleParameters(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    public void putTriangleParameters(UUID id, TriangleParameters parameters) {
        TriangleParameters copy = null;
        if (parameters != null) {
            copy = parameters.clone();
        }
        map.put(id, copy);
    }

    public Set<UUID> getKeys() {
        return map.keySet();
    }

    public boolean remove(UUID key, TriangleParameters value) { // TODO null, возможно, проверка не нужна
        return map.remove(key, value);
    }

    public Optional<TriangleParameters> remove(UUID key) {
        return Optional.ofNullable(map.remove(key));
    }

    private Warehouse() {
        map = new HashMap<>();
    }
}
