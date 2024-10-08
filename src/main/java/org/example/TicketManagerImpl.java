package org.example;

public class TicketManagerImpl implements TicketManager {
    private Ticket[] heap;
    private int size;

    private static final int CAPACITY = 10;
    private static final int INCREASE_FACTOR = 2;

    public TicketManagerImpl() {
        this.heap = new Ticket[CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(Ticket ticket) {
        if (checkCapacity()) {
            heap[size] = ticket;
            checkRules(size++);
        }
    }

    private void checkRules(int cur) {
        int par = getParent(cur);
        Ticket value = heap[cur];
        while (cur > 0 && value.compareTo(heap[par]) > 0) {
            heap[cur] = heap[par];
            cur = par;
            par = getParent(cur);
        }
        heap[cur] = value;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private boolean checkCapacity() {
        if (heap.length == size) {
            Ticket[] newHeap = new Ticket[heap.length * INCREASE_FACTOR];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }

        return true;
    }

    @Override
    public Ticket next() {
        if (size == 0) {
            return null;
        }

        Ticket res = heap[0];
        int newSize = --size;
        heap[0] = heap[newSize];
        heap[newSize] = null;
        heapify(0);
        return res;
    }

    private void heapify(int i) {
        int left;
        int right;
        int largest;

        while (heap[i] != null) {
            largest = i;
            left = 2 * i + 1;
            right = 2 * i + 2;
            if (left < heap.length && bigger(largest, left)) {
                largest = left;
            }
            if (right < heap.length && bigger(largest, right)) {
                largest = right;
            }
            if (largest == i) {
                break;
            }
            Ticket tmp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = tmp;
            i = largest;
        }
    }

    private boolean bigger(int a, int b) {
        return heap[b] != null && heap[a].compareTo(heap[b]) < 0;
    }
}
