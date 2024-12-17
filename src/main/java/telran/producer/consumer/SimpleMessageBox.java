package telran.producer.consumer;

public class SimpleMessageBox implements MessageBox {
    private String message;

    @Override
    public synchronized void put(String message) throws InterruptedException {
        while (this.message != null) {
            wait();
        }
        this.message = message;
        notifyAll();
    }

    @Override
    public synchronized String take() throws InterruptedException {
        while (message == null) {
            wait();
        }
        String msg = message;
        message = null;
        notifyAll();
        return msg;
    }

    @Override
    public synchronized String poll() {
        return message;
    }

}
