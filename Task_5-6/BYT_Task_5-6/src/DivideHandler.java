class DivideHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void process(Operation request) {
        if ("divide".equals(request.operation)) {
            if (request.number2 == 0) {
                System.out.println("Cannot divide by zero.");
            } else {
                System.out.println(request.number1 + " / " + request.number2 + " = " + (request.number1 / request.number2));
            }
        } else if (nextHandler != null) {
            nextHandler.process(request);
        }
    }
}