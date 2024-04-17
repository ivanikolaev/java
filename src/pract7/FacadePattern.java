package pract7;

// Фасад
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.freeze();
        memory.load(123, hardDrive.read(1234, 456));
        cpu.jump(123);
        cpu.execute();
    }
}

// Системные компоненты
class CPU {
    public void freeze() {
        System.out.println("CPU freeze");
    }

    public void jump(int position) {
        System.out.println("CPU jump to " + position);
    }

    public void execute() {
        System.out.println("CPU execute");
    }
}

class Memory {
    public void load(int position, String data) {
        System.out.println("Memory load at position " + position + " data: " + data);
    }
}

class HardDrive {
    public String read(long lba, int size) {
        System.out.println("HardDrive read at LBA " + lba + " size " + size);
        return "Data from hard drive";
    }
}

// Тесты
public class FacadePattern {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
