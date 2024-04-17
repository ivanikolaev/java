package pract6;

interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
interface AbstractProductA {
    void operationA();
}
interface AbstractProductB {
    void operationB();
}

class ConcreteProductA1 implements AbstractProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA1 operation");
    }
}
class ConcreteProductB1 implements AbstractProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB1 operation");
    }
}
class ConcreteFactory implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}
public class AbstractFactoryTest {
    public static void testAbstractFactory() {
        System.out.println("\nTesting Abstract Factory Pattern...");
        AbstractFactory factory = new ConcreteFactory();
        AbstractProductA productA = factory.createProductA();
        AbstractProductB productB = factory.createProductB();
        productA.operationA();
        productB.operationB();
    }
}