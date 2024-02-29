package pract3;

import java.util.HashSet;
import java.util.Set;

public class SynchronizedSet<E> {
    private final Set<E> set = new HashSet<>();

    public synchronized int size() {
        return set.size();
    }

    public synchronized boolean isEmpty() {
        return set.isEmpty();
    }

    public synchronized boolean contains(Object o) {
        return set.contains(o);
    }

    public synchronized boolean add(E e) {
        return set.add(e);
    }

    public synchronized boolean remove(Object o) {
        return set.remove(o);
    }

    public synchronized void clear() {
        set.clear();
    }

    public static void main(String[] args) {
        SynchronizedSet<Integer> set = new SynchronizedSet<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                set.add(i);
                System.out.println("Added: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                set.add(i);
                System.out.println("Added: " + i);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size after adding: " + set.size());
        System.out.println("Contains 5: " + set.contains(5));
        set.remove(5);
        set.remove(15);
        System.out.println("Size after removing: " + set.size());
        set.clear();
        System.out.println("Size after clearing: " + set.size());
    }
}
