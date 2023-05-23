package edu.ntnu.idatt2001.model.game;

import edu.ntnu.idatt2001.model.game.*;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.HealthGoal;
import edu.ntnu.idatt2001.model.goals.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Tests for the Game class")
class GameTest {
  Player player;
  Story story;
  Passage openingPassage;
  List<Goal> goals;
  Game game;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder("PlayerName")
      .health(10)
      .gold(10)
      .score(10)
      .build();
      
    openingPassage = new Passage("PassageTitle", "PassageTitle");
    story = new Story("StoryTitle", openingPassage);
    goals = new ArrayList<>();
    goals.add(new ScoreGoal(10));
    goals.add(new HealthGoal(10));
    game = new Game(player, story, goals);
  }

  @Nested
  @DisplayName("Game Getters")
  class GameGetters {
    @Test
    @DisplayName("getPlayer should return the player")
    void getPlayer() {
      assertEquals(player, game.getPlayer());
    }

    @Test
    @DisplayName("getStory should return the story")
    void getStory() {
      assertEquals(story, game.getStory());
    }

    @Test
    @DisplayName("getGoals should return the goals")
    void getGoals() {
      assertEquals(goals, game.getGoals());
    }

    @Test
    @DisplayName("begin should return the opening passage")
    void begin() {
      assertEquals(openingPassage, game.begin());
    }

    @Test
    @DisplayName("go should return the passage based on the given link")
    void go() {
      assertEquals(openingPassage, game.go(new Link(openingPassage.getTitle(), openingPassage.getTitle())));
    }
  }

  @Nested
  @DisplayName("Exception Handling")
  class ExceptionHandling {
    @Test
    @DisplayName("New game with null player should throw NullPointerException")
    void createGameWithNullPlayer() {
      assertThrows(NullPointerException.class, () -> new Game(null, story, goals));
    }

    @Test
    @DisplayName("new Game with null Story should throw NullPointerException")
    void createGameWithNullStory() {
      assertThrows(NullPointerException.class, () -> new Game(player, null, goals));
    }

    @Test
    @DisplayName("New Game with null Goals should throw NullPointerException")
    void createGameWithNullGoals() {
      assertThrows(NullPointerException.class, () -> new Game(player, story, null));
    }

    @Test
    @DisplayName("Go with null Link should throw NullPointerException")
    void goWithNullLink() {
      assertThrows(NullPointerException.class, () -> game.go(null));
    }
  }
}