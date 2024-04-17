package pract5;

public class SingletonStaticBlock {
    private static final SingletonStaticBlock instance;

    static {
        try {
            instance = new SingletonStaticBlock();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    private SingletonStaticBlock() {}

    public static SingletonStaticBlock getInstance() {
        return instance;
    }
}
