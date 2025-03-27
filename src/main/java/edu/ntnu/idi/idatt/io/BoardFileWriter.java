package edu.ntnu.idi.idatt.io;

import edu.ntnu.idi.idatt.model.Board;
import java.io.IOException;

public interface BoardFileWriter {

  public void writeBoard(Board board) throws IOException;

  public static void handleFileError(IOException e) {
    System.out.println(e.getMessage());
  }
}

