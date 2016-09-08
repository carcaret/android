package practicas.cursoandroid.practicasandroid.handler;

public class MyMessage {

    private String destination;
    private String message;

    public MyMessage() {
    }

    public MyMessage(String destination, String message) {
        this.destination = destination;
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
