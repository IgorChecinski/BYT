interface ComputerBuilder {
    void reset();
    void setCPU(String cpu);
    void setGPU(String gpu);
    void setRAM(String ram);
    void setStorage(String storage);
    void setOperatingSystem(String operatingSystem);
    Computer build();
}
