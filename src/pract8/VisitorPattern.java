package pract8;

import java.util.ArrayList;
import java.util.List;

// Интерфейс посетителя
interface Visitor {
    void visitElementA(ElementA element);
    void visitElementB(ElementB element);
}

// Интерфейс элемента
interface Element {
    void accept(Visitor visitor);
}

// Конкретный элемент
class ConcreteVisitor implements Visitor {
    @Override
    public void visitElementA(ElementA element) {
        System.out.println("ConcreteVisitor is visiting ElementA: " + element.operationA());
    }

    @Override
    public void visitElementB(ElementB element) {
        System.out.println("ConcreteVisitor is visiting ElementB: " + element.operationB());
    }
}

class ElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitElementA(this);
    }

    public String operationA() {
        return "ElementA operation";
    }
}

// Конкретный элемент B
class ElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitElementB(this);
    }

    public String operationB() {
        return "ElementB operation";
    }
}

// Класс структуры, хранящей элементы
class Structure {
    private List<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        elements.add(element);
    }

    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}

// Тесты
public class VisitorPattern {
    public static void main(String[] args) {
        Structure structure = new Structure();
        structure.addElement(new ElementA());
        structure.addElement(new ElementB());
        structure.addElement(new ElementA());
        structure.addElement(new ElementB());

        Visitor visitor = new ConcreteVisitor();
        structure.accept(visitor);
    }
}