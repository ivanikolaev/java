package pract6;

class Prototype implements Cloneable {
    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    void operation() {
        System.out.println("Prototype operation");
    }
}
public class PrototypeTest {
    public static void testPrototype() {
        System.out.println("\nTesting Prototype Pattern...");
        try {
            Prototype prototype = new Prototype();
            Prototype clonedPrototype = prototype.clone();
            prototype.operation();
            clonedPrototype.operation();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}