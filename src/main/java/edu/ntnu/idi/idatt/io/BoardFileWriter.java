package edu.ntnu.idi.idatt.io;

import edu.ntnu.idi.idatt.model.Board;
import java.io.IOException;

public interface BoardFileWriter {

  public void writeBoard(Board board) throws IOException;

  /**
   * Default method to handle errors during file writing.
   * Can be used in implementing classes for logging purposes.
   *
   * @param e the IOExeption encountered during file processing
   */
  default void handleFileError(IOException e) {
    System.err.println("Error writing board file: " + e.getMessage());
  }
}

