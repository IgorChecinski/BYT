class Computer {
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String operatingSystem;

    // Setters for each component
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    // toString method to display computer specifications
    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
