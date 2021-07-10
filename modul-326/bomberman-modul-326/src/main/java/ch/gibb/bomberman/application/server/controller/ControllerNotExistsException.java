package ch.gibb.bomberman.application.server.controller;

public class ControllerNotExistsException extends Exception {
    public ControllerNotExistsException(String message) {
        super(message);
    }
}