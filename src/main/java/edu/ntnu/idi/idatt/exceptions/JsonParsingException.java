package edu.ntnu.idi.idatt.exceptions;

/**
 * The JsonParsingException is an exception thrown when an issue occurs in parsing a JSON file
 */
public class JsonParsingException extends RuntimeException {

  public JsonParsingException(String message) {
    super(message);
  }

  public JsonParsingException(String message, Throwable cause) {
    super(message, cause);
  }

}
