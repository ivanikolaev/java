package pract5;

public class Main {
    public static void main(String[] args) {
        // Тестирование ленивой инициализации (Lazy Initialization)
        SingletonLazy lazyInstance1 = SingletonLazy.getInstance();
        SingletonLazy lazyInstance2 = SingletonLazy.getInstance();

        System.out.println("Lazy Initialization:");
        System.out.println("Instance 1 hash code: " + lazyInstance1.hashCode());
        System.out.println("Instance 2 hash code: " + lazyInstance2.hashCode());
        System.out.println("Are instances equal? " + (lazyInstance1 == lazyInstance2));
        System.out.println();

        // Тестирование Double-Checked Locking
        SingletonDoubleChecked doubleCheckedInstance1 = SingletonDoubleChecked.getInstance();
        SingletonDoubleChecked doubleCheckedInstance2 = SingletonDoubleChecked.getInstance();

        System.out.println("Double-Checked Locking:");
        System.out.println("Instance 1 hash code: " + doubleCheckedInstance1.hashCode());
        System.out.println("Instance 2 hash code: " + doubleCheckedInstance2.hashCode());
        System.out.println("Are instances equal? " + (doubleCheckedInstance1 == doubleCheckedInstance2));
        System.out.println();

        // Тестирование использования статического блока инициализации
        SingletonStaticBlock staticBlockInstance1 = SingletonStaticBlock.getInstance();
        SingletonStaticBlock staticBlockInstance2 = SingletonStaticBlock.getInstance();

        System.out.println("Static Block Initialization:");
        System.out.println("Instance 1 hash code: " + staticBlockInstance1.hashCode());
        System.out.println("Instance 2 hash code: " + staticBlockInstance2.hashCode());
        System.out.println("Are instances equal? " + (staticBlockInstance1 == staticBlockInstance2));
    }
}
