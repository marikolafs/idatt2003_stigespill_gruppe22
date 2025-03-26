package edu.ntnu.idi.idatt.io;

import edu.ntnu.idi.idatt.model.Board;
import java.io.IOException;
import java.nio.file.Path;


/**
 * The BoardFileReader interface provides a method for reading a board configuration from a file.
 * Implementations of this interface should define how the board is read and parsed from
 * the given file path.
 */
public interface BoardFileReader {
  /**
   * Reads a board configuration from the specified file path.
   *
   * @param path the path to the file containing the board configuration
   * @return the Board object representing the board configuration
   * @throws IOException if an error occurs while reading the file
   */
  public Board readBoard (Path path) throws IOException;

  /**
   * Default method to handle errors during file reading.
   * Can be used in implementing classes for logging purposes.
   *
   * @param e the IOExeption encountered during file processing
   */
  default void handleFileError(IOException e) {
    System.err.println("Error reading board file: " + e.getMessage());
  }


}
