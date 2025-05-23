# Board Game project IDATT2003
### Contributers
  📌Marìa Ólafsdóttir

  📌Christina Bye

## 📄Project description
This project was developed as part of the primary assignment in the IDATT2003 (Programmering 2) course in the Bachelor's program in Computer Engineering at NTNU.
The objective of this project was to develop a flexible GUI based board game framework for different kinds of board games.

## System requirements
- Java JDK 21 or higher
- JavaFX 21 or higher
- Maven for building and running

## Project structure
$ tree
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/ntnu/idi/idatt/
│   │   │       ├── controller/
│   │   │       │   ├── GameBoardController.java
│   │   │       │   ├── PlayerController.java
│   │   │       │   └── SceneManager.java
│   │   │       ├── engine/
│   │   │       │   └── BoardGame.java
│   │   │       ├── exceptions/
│   │   │       │   └── JsonParsingException.java
│   │   │       ├── io/
│   │   │       │   └── BoardFileReaderGson.java
│   │   │       ├── model/
│   │   │       │   ├── actions/
│   │   │       │   │   ├── EntryAction.java
│   │   │       │   │   ├── HoldAction.java
│   │   │       │   │   ├── HomeEntryAction.java
│   │   │       │   │   ├── LadderAction.java
│   │   │       │   │   └── ReturnAction.java
│   │   │       │   ├── Board.java
│   │   │       │   ├── Player.java
│   │   │       │   └── Tile.java
│   │   │       └── view/
│   │   │           └── GameBoardView.java
│   │   └── resources/
│   │       ├── ExampleBoard.json
│   │       └── images/
│   └── test/
│       └── java/
│           └── edu/ntnu/idi/idatt/
│               ├── engine/
│               │   └── BoardGameTest.java
│               └── io/
│                   └── BoardFileReaderGsonTest.java
└── pom.xml

## Link to repository
https://github.com/marikolafs/idatt2003_stigespill_gruppe22

## How to run the project
#### Run using Maven
mvn javafx:run

#### Run using IntelliJ
![image](https://github.com/user-attachments/assets/c35a4af2-afd5-466d-9a6a-9412ff042270)

## How to run the tests
#### Run tests in Mavne
mvn test
#### Run tests in IntelliJ
The IntelliJ Run Application and Run All Tests should be bundled with the repository under .idea/runConfigurations
![image](https://github.com/user-attachments/assets/eb996366-f0e0-4954-b6cf-2d96c7eaadf1)



## Features
- Players can choose from a set of predefined game variants: Multiple variants of Snakes and Ladders and Ludo.
- Supports 2-5 players.
- Each player selects a unique game piece/icon from a predefined set and names their character.
- The game uses two dice per turn, and the piece moves according to the sum of the roll.
- Different squares on the board trigger different actions (move up a ladder, fall down, etc.).
- The first player to reach square 90 (depending on the board variant) wins the game.
