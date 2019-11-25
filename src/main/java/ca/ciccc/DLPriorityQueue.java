package ca.ciccc;

import java.util.LinkedList;

public class DLPriorityQueue<K, V> implements VCPriorityQueue {
    private LinkedList<Entry> priorityQueue;

    public LinkedList<Entry> getPriorityQueue() {
        return priorityQueue;
    }

    public DLPriorityQueue() {
        this.priorityQueue = new LinkedList<>();
    }

    public DLPriorityQueue(LinkedList<Entry> priorityQueue) {
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
        if (priorityQueue.size() == 0) {
            priorityQueue.add(entry);
            return entry;
        }
        for (int i = 0; i < priorityQueue.size(); i++) {
            if (key.compareTo(priorityQueue.get(i).key) < 0) {
                priorityQueue.add(i, entry);
                break;
            } else {
                if (i == priorityQueue.size() - 1) {
                    priorityQueue.add(i + 1, entry);
                    break;
                }
            }
        }
        return entry;
    }

    @Override
    public Entry peek() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        return priorityQueue.get(0);
    }

    @Override
    public Entry dequeueMin() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        return priorityQueue.remove(0);
    }

    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        LinkedList<Entry> tmpList = (LinkedList<Entry>) priorityQueue.clone();
        DLPriorityQueue newDL = new DLPriorityQueue(tmpList);
        DLPriorityQueue otherDL = (DLPriorityQueue) other;
        LinkedList<Entry> otherPQ = otherDL.getPriorityQueue();

        for (Entry e : otherPQ) {
            newDL.enqueue(e.key, e.value);
        }

        return newDL;
    }
}
