package edu.ntnu.idi.idatt;

import java.util.HashMap;
import java.util.Map;

public class Board {
  Map<Integer, Tile> tiles = new HashMap<>();
  Game game;


  public void addTile(Tile tile) {
    for (int i = 0; i < game.getNumberOfTiles(); i++) {
      tiles.put(i, tile);
    }
  }

  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }
}

/*Representerer selve spillbrettet bestående av en samling felt (engelsk: Tile). Spillbrettet holder også
en totaloversikt over alle felt. Hvert felt har en unik ID som spillbrettet kan bruke for å holde oversikt
over samtlige felt i spillet. Dette blir viktig i forbindelse med stige-funksjonaliteten: spiller skal hoppe
fra et felt til et annet felt.*/