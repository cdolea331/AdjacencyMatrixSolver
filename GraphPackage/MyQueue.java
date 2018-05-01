//
// Name: Olea, Carlos
// Project: 2
// Due: December 4 2017
// Course: cs-241-02-f17
//
// Description:
// Read a text input to create a directed graph, then find the shortest path between two cities on the graph.
//
package GraphPackage;

import java.util.*;

/**
 * Personally implemented Queue data structure
 * @param <T> Generic type
 */
public class MyQueue<T> implements QueueInterface<T>{
    private LinkedList<T> entries;

    public MyQueue(){
        entries = new LinkedList<>();
    }


    public void sort(Comparator<T> comparator){
        entries.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return comparator.compare(o1,o2);
            }
        });
    }
    public void enqueue(T newEntry){
        entries.addLast(newEntry);
    }

    public T dequeue(){
        return entries.removeFirst();
    }



    public boolean isEmpty(){
        return entries.isEmpty();
    }
    public T poll(){
        T returnData = null;
        try{
            returnData = entries.removeFirst();
        }catch (NoSuchElementException e){

        }
        return returnData;
    }

    @Override
    public T getFront() {
        return entries.get(0);
    }
    @Override
    public void clear() {
        entries.clear();
    }

    public boolean has(T data){
        for (int i = 0; i < entries.size(); i++){
            if(entries.get(0).equals(data));
                return true;
        }
        return false;
    }
}
