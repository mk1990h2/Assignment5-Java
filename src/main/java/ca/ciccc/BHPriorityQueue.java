package ca.ciccc;

import java.util.Arrays;

public class BHPriorityQueue implements VCPriorityQueue {
    private static final int BRANCH = 2;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private Entry[] priorityQueue;
    private int size;

    public BHPriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public BHPriorityQueue(int capacity) {
        this.size = 0;
        this.priorityQueue = new Entry[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry enqueue(Comparable key, Object value) throws IllegalArgumentException {
        Entry entry = new Entry(key, value);
        if (isFull()) {
            priorityQueue = grow(size + 1);
        }
        priorityQueue[size++] = entry;
        heapifyUp(size - 1);
        return entry;
    }

    @Override
    public Entry peek() {
        return priorityQueue[0];
    }

    @Override
    public Entry dequeueMin() {
        if (isEmpty()) {
            return null;
        }
        Entry dequeue = priorityQueue[0];
        priorityQueue[0] = priorityQueue[size - 1];
        size--;
        heapifyDown();
        return dequeue;
    }

    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        return null;
    }

    private boolean isFull() {
        return size == priorityQueue.length;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / BRANCH;
    }

    private Entry getParent(int childIndex) {
        return priorityQueue[(childIndex - 1) / BRANCH];
    }

    private Entry getChild(int parentIndex, int child) {
        return priorityQueue[BRANCH * parentIndex + child];
    }

    private int getChildIndex(int parentIndex, int child) {
        return BRANCH * parentIndex + child;
    }

    private Entry[] grow(int minCapacity) {
        return priorityQueue = Arrays.copyOf(priorityQueue, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = priorityQueue.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity <= minCapacity) {
            if (minCapacity < 0 || minCapacity > MAX_ARRAY_SIZE) {
                throw new OutOfMemoryError("integer overflow");
            }
            return minCapacity;
        }
        return (newCapacity <= MAX_ARRAY_SIZE) ? newCapacity : Integer.MAX_VALUE;
    }

    private void heapifyUp(int i) {
        Entry temp = priorityQueue[i];
        while (i > 0 && temp.key.compareTo(getParent(i).key) > 0) {
            priorityQueue[i] = priorityQueue[getParentIndex(i)];
            i = getParentIndex(i);
        }
        priorityQueue[i] = temp;
    }

    private void heapifyDown() {
        int child;
        int i = 0;
        Entry temp = priorityQueue[i];
        while (getChildIndex(i, 1) < size) {
            child = maxChild(i);
            if (temp.key.compareTo(priorityQueue[child]) < 0) {
                priorityQueue[i] = priorityQueue[child];
            } else {
                break;
            }
            i = child;
        }
        priorityQueue[i] = temp;
    }

    private int maxChild(int parentIndex) {
        int leftChild = getChildIndex(parentIndex, 1);
        int rightChild = getChildIndex(parentIndex, 2);
        return priorityQueue[leftChild].key.compareTo(priorityQueue[rightChild]) > 0 ? leftChild : rightChild;
    }
}