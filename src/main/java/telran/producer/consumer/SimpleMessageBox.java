package telran.producer.consumer;

public class SimpleMessageBox implements MessageBox {
    private String message;

    @Override
    public synchronized void put(String message) {
        this.message = message;
        notify();
    }

    @Override
    public synchronized String take() throws InterruptedException {
        while (message == null) {
            wait();
        }
        String msg = message;
        message = null;
        return msg; // may not be null
    }

    @Override
    public synchronized String poll() {
        return message; // maybe null
    }

}
