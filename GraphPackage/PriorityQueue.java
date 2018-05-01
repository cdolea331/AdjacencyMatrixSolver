package GraphPackage;

import java.util.Comparator;

/**
 * Personally implemented priority queue data structure
 * @param <T> Generic type
 */
public class PriorityQueue<T> extends MyQueue<T> {
    private Comparator<T> comparator;
    public PriorityQueue(Comparator<T> comparator){
        this.comparator = comparator;
    }
    @Override
    public void enqueue(T newEntry) {
        super.enqueue(newEntry);
        super.sort(comparator);
    }
}
