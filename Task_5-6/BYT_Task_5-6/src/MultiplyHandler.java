class MultiplyHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void process(Operation request) {
        if ("multiply".equals(request.operation)) {
            System.out.println(request.number1 + " * " + request.number2 + " = " + (request.number1 * request.number2));
        } else if (nextHandler != null) {
            nextHandler.process(request);
        }
    }
}