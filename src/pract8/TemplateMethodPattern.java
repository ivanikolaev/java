package pract8;

// Абстрактный класс с шаблонным методом
abstract class AbstractClass {
    // Шаблонный метод, который определяет скелет алгоритма
    public void templateMethod() {
        operation1(); // Вызываемый метод
        operation2(); // Вызываемый метод
        operation3(); // Метод, который может быть переопределен в подклассах
    }

    // Абстрактные операции, которые должны быть реализованы в подклассах
    protected abstract void operation1();
    protected abstract void operation2();

    // Операция по умолчанию, которая может быть переопределена в подклассах
    protected void operation3() {
        System.out.println("AbstractClass default operation3");
    }
}

// Конкретная реализация абстрактного класса
class ConcreteClass extends AbstractClass {
    @Override
    protected void operation1() {
        System.out.println("ConcreteClass operation1");
    }

    @Override
    protected void operation2() {
        System.out.println("ConcreteClass operation2");
    }

    // Переопределяем метод, который был определен в абстрактном классе
    @Override
    protected void operation3() {
        System.out.println("ConcreteClass overridden operation3");
    }
}

// Тесты
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass template = new ConcreteClass();
        template.templateMethod(); // Вызов шаблонного метода
    }
}
