interface Handler {
    void setNextHandler(Handler handler);
    void process(Operation request);
}