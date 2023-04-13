package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.game.*;
import edu.ntnu.idatt2001.goals.Goal;
import edu.ntnu.idatt2001.goals.HealthGoal;
import edu.ntnu.idatt2001.goals.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
  class GameGetters {
    @Test
    void getPlayer() {
      assertEquals(player, game.getPlayer());
    }

    @Test
    void getStory() {
      assertEquals(story, game.getStory());
    }

    @Test
    void getGoals() {
      assertEquals(goals, game.getGoals());
    }

    @Test
    void begin() {
      assertEquals(openingPassage, game.begin());
    }

    @Test
    void go() {
      assertEquals(openingPassage, game.go(new Link(openingPassage.getTitle(), openingPassage.getTitle())));
    }
  }

  @Nested
  class ExceptionHandling {
    @Test
    void createGameWithNullPlayer() {
      assertThrows(NullPointerException.class, () -> new Game(null, story, goals));
    }

    @Test
    void createGameWithNullStory() {
      assertThrows(NullPointerException.class, () -> new Game(player, null, goals));
    }

    @Test
    void createGameWithNullGoals() {
      assertThrows(NullPointerException.class, () -> new Game(player, story, null));
    }

    @Test
    void goWithNullLink() {
      assertThrows(NullPointerException.class, () -> game.go(null));
    }
  }
}