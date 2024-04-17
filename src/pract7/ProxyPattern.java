package pract7;

interface Service {
    void request();
}


class RealService implements Service {
    @Override
    public void request() {
        System.out.println("RealService: Handling request");
    }
}


class ProxyService implements Service {
    private RealService realService;

    @Override
    public void request() {
        if (realService == null) {
            realService = new RealService();
        }
        preRequest();
        realService.request();
        postRequest();
    }

    private void preRequest() {
        System.out.println("ProxyService: Pre-processing request");
    }

    private void postRequest() {
        System.out.println("ProxyService: Post-processing request");
    }
}

// Тесты
public class ProxyPattern {
    public static void main(String[] args) {
        Service proxy = new ProxyService();
        proxy.request();
    }
}
