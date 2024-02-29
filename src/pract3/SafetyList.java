package pract3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SafetyList<E> {
    private final List<E> list = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);

    public int size() {
        return list.size();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public boolean add(E e) {
        try {
            semaphore.acquire();
            boolean result = list.add(e);
            return result;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }

    public boolean remove(Object o) {
        try {
            semaphore.acquire();
            boolean result = list.remove(o);
            return result;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }

    public void clear() {
        try {
            semaphore.acquire();
            list.clear();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SafetyList<Integer> list = new SafetyList<>();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println("Added: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                list.add(i);
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
        System.out.println("Size after adding: " + list.size());
        System.out.println("Contains 5: " + list.contains(5));
        list.remove(Integer.valueOf(5));
        list.remove(Integer.valueOf(15));
        System.out.println("Size after removing: " + list.size());
        list.clear();
        System.out.println("Size after clearing: " + list.size());
    }

}


