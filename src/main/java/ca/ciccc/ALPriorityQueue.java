package ca.ciccc;

import java.util.ArrayList;

public class ALPriorityQueue<K, V> implements VCPriorityQueue {
    private ArrayList<Entry> priorityQueue;

    public ALPriorityQueue() {
        this.priorityQueue = new ArrayList();
    }

    public ALPriorityQueue(ArrayList<Entry> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    @Override
    public int size() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public Entry enqueue(Comparable key, Object value) throws IllegalArgumentException {
        Entry entry = new Entry(key, value);
        priorityQueue.add(entry);
        return entry;
    }

    @Override
    public Entry peek() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        if (priorityQueue.size() == 1) {
            return priorityQueue.get(0);
        }
        Comparable key = priorityQueue.get(0).key;
        int index = 0;
        for (int i = 1; i < priorityQueue.size(); i++) {
            if (key.compareTo(priorityQueue.get(i).key) > 0) {
                index = i;
            }
        }
        return priorityQueue.get(index);
    }

    @Override
    public Entry dequeueMin() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        Entry e = peek();
        priorityQueue.remove(e);
        return e;
    }

    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        ALPriorityQueue al = (ALPriorityQueue) other;
        ArrayList<Entry> newArray = (ArrayList<Entry>) priorityQueue.clone();
        newArray.addAll(al.priorityQueue);
        return new ALPriorityQueue(newArray);
    }
}
