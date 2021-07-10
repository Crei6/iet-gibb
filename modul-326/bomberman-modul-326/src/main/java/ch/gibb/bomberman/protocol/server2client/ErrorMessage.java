package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

public class ErrorMessage implements Message {
  private String errorMessage;

  public ErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
