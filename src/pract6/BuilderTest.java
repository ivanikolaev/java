package pract6;

class ProductBuilder {
    private String part1;
    private String part2;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public void show() {
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }
}
interface Builder {
    void buildPart1();
    void buildPart2();
    ProductBuilder getResult();
}
class ConcreteBuilder implements Builder {
    private ProductBuilder product = new ProductBuilder();

    @Override
    public void buildPart1() {
        product.setPart1("Part 1 built");
    }

    @Override
    public void buildPart2() {
        product.setPart2("Part 2 built");
    }

    @Override
    public ProductBuilder getResult() {
        return product;
    }
}
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}
public class BuilderTest {
    public static void testBuilder() {
        System.out.println("\nTesting Builder Pattern...");
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        ProductBuilder productBuilder = builder.getResult();
        productBuilder.show();
    }
}