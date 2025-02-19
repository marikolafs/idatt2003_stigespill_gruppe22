package edu.ntnu.idi.idatt;

import java.util.HashMap;
import java.util.Map;

public class Board {
  Map<Integer, Tile> tiles = new HashMap<>();

  public void addTile(Tile tile) {

  }

  public Tile getTile(int tileId) {
    
  }

}

/*Representerer selve spillbrettet bestående av en samling felt (engelsk: Tile). Spillbrettet holder også
en totaloversikt over alle felt. Hvert felt har en unik ID som spillbrettet kan bruke for å holde oversikt
over samtlige felt i spillet. Dette blir viktig i forbindelse med stige-funksjonaliteten: spiller skal hoppe
fra et felt til et annet felt.*/