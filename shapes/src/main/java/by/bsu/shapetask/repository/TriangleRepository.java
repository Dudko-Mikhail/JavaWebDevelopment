package by.bsu.shapetask.repository;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.repository.TriangleSpecification;

import java.util.*;

public class TriangleRepository { // TODO implements repository
    private static TriangleRepository instance;
    private List<Triangle> triangles;

    public int size() {
        return triangles.size();
    }

    public boolean isEmpty() {
        return triangles.isEmpty();
    }

    public boolean contains(Triangle triangle) {
        return triangles.contains(triangle);
    }

    public boolean add(Triangle triangle) {
        return triangles.add(triangle);
    }

    public boolean remove(Triangle triangle) {
        return triangles.remove(triangle);
    }

    public boolean containsAll(Collection<?> c) {
        return triangles.containsAll(c);
    }

    public boolean addAll(Collection<? extends Triangle> c) {
        return triangles.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Triangle> c) {
        return triangles.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return triangles.removeAll(c);
    }

    public List<Triangle> sort(Comparator<? super Triangle> c) {
        return triangles.stream()
                        .sorted(c)
                        .toList();
    }

    public void clear() {
        triangles.clear();
    }

    public Triangle get(int index) {
        return triangles.get(index);
    }

    public Triangle set(int index, Triangle element) {
        return triangles.set(index, element);
    }

    public Triangle remove(int index) {
        return triangles.remove(index);
    }

    public List<Triangle> query(TriangleSpecification specification) {
        return triangles.stream()
                        .filter(specification)
                        .toList();
    }

    public static TriangleRepository getInstance() {
        if (instance == null) {
            instance = new TriangleRepository();
        }
        return instance;
    }

    private TriangleRepository() {
        triangles = new ArrayList<>();
    }
}
