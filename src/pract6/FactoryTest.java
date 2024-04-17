package pract6;

interface Product {
    void operation();
}
class ConcreteProduct implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProduct operation");
    }
}
abstract class Creator {
    abstract Product factoryMethod();
}
class ConcreteCreator extends Creator {
    @Override
    Product factoryMethod() {
        return new ConcreteProduct();
    }
}
public class FactoryTest {
    public static void testFactory() {
        System.out.println("Testing Factory Method Pattern...");
        Creator creator = new ConcreteCreator();
        Product product = creator.factoryMethod();
        product.operation();
    }
}