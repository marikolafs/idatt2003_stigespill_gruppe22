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
  Board readBoard(Path path) throws IOException;


}
