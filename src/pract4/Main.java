package pract4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testSimpleSubmission(); // Тестирование простой отправки задач
        testShutdown(); // Тестирование остановки сервиса
    }

    private static void testSimpleSubmission() throws ExecutionException, InterruptedException {
        System.out.println("=== Тест простой отправки задач ===");
        int numThreads = 2;
        MyExecutorService executorService = new MyExecutorService(numThreads);


        Future<Integer> future1 = executorService.submit(() -> {
            Thread.sleep(1000);
            System.out.println("Результат задачи 1: " + 1);
            return 1;
        });

        Future<Integer> future2 = executorService.submit(() -> {
            Thread.sleep(500);
            System.out.println("Результат задачи 2: " + 2);
            return 2;
        });


        int result1 = future1.get();
        int result2 = future2.get();

        System.out.println("Результат первой задачи: " + result1);
        System.out.println("Результат второй задачи: " + result2);

        executorService.shutdown();
    }

    private static void testShutdown() throws ExecutionException, InterruptedException {
        System.out.println("\n=== Тест остановки сервиса ===");
        int numThreads = 2;
        MyExecutorService executorService = new MyExecutorService(numThreads);

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            tasks.add(() -> {
                Thread.sleep(1000);
                System.out.println("Результат задачи " + finalI + ": " + finalI);
                return finalI;
            });
        }

        // Отправляем задачи на выполнение
        List<Future<Integer>> futures = executorService.invokeAll(tasks);

        // Останавливаем сервис
        executorService.shutdown();

        // Проверяем, что все задачи были выполнены
        for (Future<Integer> future : futures) {
            System.out.println("Результат задачи: " + future.get());;
        }

    }


}
