package apirelatorios.Relatorio.application.controller.exception;

public class UnexpectedError extends RuntimeException {

    public UnexpectedError(String message) {
        super(message);
    }
}
