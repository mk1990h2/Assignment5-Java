package ca.ciccc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBHPriorityQueue {
    @Test
    public void testSize() {
        DLPriorityQueue queue = new DLPriorityQueue();
        // case: size = 0
        assertEquals(0, queue.size());
        // case: size > 0
        queue.enqueue(1, "test");
        assertEquals(1, queue.size());
    }

    @Test
    public void testIsEmpty() {
        DLPriorityQueue queue = new DLPriorityQueue();
        // case: queue is empty
        assertEquals(true, queue.isEmpty());

        // case: queue is NOT empty
        queue.enqueue(1, "test");
        assertEquals(false, queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        DLPriorityQueue queue = new DLPriorityQueue();
        queue.enqueue(1, "test");
        Entry result = queue.enqueue(1, "test");
        assertEquals(1, result.key);
        assertEquals("test", result.value);
    }

    @Test
    public void testPeek() {
        DLPriorityQueue queue = new DLPriorityQueue();
        // case: queue is empty
        assertEquals(null, queue.peek());

        // case: queue has 1 element
        queue.enqueue(1, "test1");
        Entry e1 = queue.peek();
        assertEquals(1, e1.key);
        assertEquals("test1", e1.value);

        // case: queue has more than 1 elements
        Entry result = queue.enqueue(2, "test2");
        Entry e2 = queue.peek();
        assertEquals(1, e2.key);
        assertEquals("test1", e2.value);
    }

    @Test
    public void testDequeueMin() {
        DLPriorityQueue queue = new DLPriorityQueue();
        // case: queue is empty
        assertEquals(null, queue.dequeueMin());

        // case: queue is NOT empty
        queue.enqueue(1, "test1");
        queue.enqueue(2, "test2");
        Entry e1 = queue.dequeueMin();
        assertEquals(1, e1.key);
        assertEquals("test1", e1.value);
        assertEquals(1, queue.size());

        Entry e2 = queue.dequeueMin();
        assertEquals(2, e2.key);
        assertEquals("test2", e2.value);
        assertEquals(0, queue.size());
    }

    @Test
    public void testMerge() {
        DLPriorityQueue queue1 = new DLPriorityQueue();
        DLPriorityQueue queue2 = new DLPriorityQueue();

        // case: both of queues are empty
        assertEquals(true, queue1.merge(queue2).isEmpty());

        // case: either of queue is empty
        queue2.enqueue(2, "queue2-1");
        DLPriorityQueue result1 = (DLPriorityQueue) queue1.merge(queue2);
        assertEquals(true, queue1.isEmpty());
        assertEquals(1, queue2.size());
        assertEquals(1, result1.size());
        assertEquals(2, result1.peek().key);
        assertEquals("queue2-1", result1.peek().value);

        // case: both of queues are NOT empty
        queue1.enqueue(1, "queue1-1");
        queue1.enqueue(3, "queue1-2");
        DLPriorityQueue result2 = (DLPriorityQueue) queue1.merge(queue2);
        assertEquals(2, queue1.size());
        assertEquals(1, queue2.size());
        assertEquals(3, result2.size());
        assertEquals(1, result2.peek().key);
        assertEquals("queue1-1", result2.peek().value);

    }
}
