public class Main {
    public static void main(String[] args) {

        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        gamingBuilder.setCPU("Intel i7");
        gamingBuilder.setGPU("NVIDIA RTX 3080");
        gamingBuilder.setRAM("16GB");
        gamingBuilder.setStorage("1TB SSD");
        gamingBuilder.setOperatingSystem("Windows 10");
        Computer gamingComputer = gamingBuilder.build();
        System.out.println("Gaming Computer: " + gamingComputer);

        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        officeBuilder.setCPU("Efficient CPU");
        officeBuilder.setGPU("Integrated GPU");
        officeBuilder.setRAM("16GB");
        officeBuilder.setStorage("512GB SSD");
        officeBuilder.setOperatingSystem("Windows 10 Home");
        Computer officeComputer = officeBuilder.build();
        System.out.println("Office Computer: " + officeComputer);
    }
}
