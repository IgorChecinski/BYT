class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.computer = new Computer();
    }

    @Override
    public void setCPU(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void setGPU(String gpu) {
        computer.setGpu(gpu);
    }

    @Override
    public void setRAM(String ram) {
        computer.setRam(ram);
    }

    @Override
    public void setStorage(String storage) {
        computer.setStorage(storage);
    }

    @Override
    public void setOperatingSystem(String operatingSystem) {
        computer.setOperatingSystem(operatingSystem);
    }

    @Override
    public Computer build() {
        return computer;
    }
}