package edu.ntnu.idatt2001.util;

import edu.ntnu.idatt2001.model.actions.player.Action;
import edu.ntnu.idatt2001.model.actions.player.ActionFactory;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Story;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StoryReader is a utility class that reads stories from files.
 * Each story consists of passages and links. Links may contain actions.
 * The passages, links, and actions are defined by specific patterns in the file text.
 * This class uses regular expressions (Patterns) to identify the different elements of a story.
 */
public class StoryReader {
  private static final Pattern PASSAGE_PATTERN = Pattern.compile("::.*");
  private static final Pattern LINK_PATTERN = Pattern.compile("\\[.*");
  private static final Pattern LINK_TEXT_PATTERN = Pattern.compile("\\[(.*?)]");
  private static final Pattern LINK_REFERENCE_PATTERN = Pattern.compile("\\((.*?)\\)");
  private static final Pattern ACTION_PATTERN = Pattern.compile("!.*");
  private static final String ACTION_DELIMITER = ":";

  /**
   * Private constructor for utility class.
   */
  private StoryReader() {
    // Empty
  }

  /**
   * Reads a Story from the file at the specified path.
   * The story is composed of a title, opening passage, and additional passages if available.
   *
   * @param path The file path to read the story from.
   * @return A Story object containing the data from the file.
   * @throws IOException If an error occurs while trying to read the file.
   */
  public static Story read(String path) throws IOException {
    Story story;

    try (Scanner scanner = new Scanner(new File(path))) {
      String storyTitle = scanner.nextLine();
      scanner.nextLine();

      Passage openingPassage = readPassage(scanner);
      scanner.nextLine();

      story = new Story(storyTitle, openingPassage);

      while (scanner.hasNext(PASSAGE_PATTERN)) {
        Passage passage = readPassage(scanner);
        story.addPassage(passage);
        if (scanner.hasNext()) {
          scanner.nextLine();
        }
      }
    }

    return story;
  }

  /**
   * Reads a Passage from the Scanner.
   * The passage includes a title, content, and any available links.
   *
   * @param scanner A Scanner object that is used to read the passage.
   * @return A Passage object containing the data read from the Scanner.
   */
  private static Passage readPassage(Scanner scanner) {
    String title = scanner.nextLine();
    title = title.substring(2);
    String content = scanner.nextLine();
    Passage passage = new Passage(title, content);

    while (scanner.hasNext(LINK_PATTERN)) {
      Link link = readLink(scanner);
      passage.addLink(link);
    }

    return passage;
  }


  /**
   * Reads a Link from the Scanner.
   * The link includes a link text, link reference, and any available actions.
   *
   * @param scanner A Scanner object that is used to read the link.
   * @return A Link object containing the data read from the Scanner.
   */
  private static Link readLink(Scanner scanner) {
    String line = scanner.nextLine();
    Matcher textMatcher = LINK_TEXT_PATTERN.matcher(line);
    Matcher referenceMatcher = LINK_REFERENCE_PATTERN.matcher(line);

    String linkText = null;
    String linkReference = null;

    while (textMatcher.find()) {
      linkText = textMatcher.group(1);
    }
    while (referenceMatcher.find()) {
      linkReference = referenceMatcher.group(1);
    }

    Link link = new Link(linkText, linkReference);

    while (scanner.hasNext(ACTION_PATTERN)) {
      Action action = readAction(scanner);
      link.addAction(action);
    }

    return link;
  }

  /**
   * Reads an Action from the Scanner.
   * The action includes an action type and action value.
   *
   * @param scanner A Scanner object that is used to read the action.
   * @return An Action object containing the data read from the Scanner.
   */
  private static Action readAction(Scanner scanner) {
    String line = scanner.nextLine();
    line = line.substring(1);
    String[] actionComponents = line.split(ACTION_DELIMITER);
    ActionFactory.ActionType actionType = parseActionType(actionComponents[0]);
    String actionValue = actionComponents[1];

    return ActionFactory.get(actionType, actionValue);
  }

  /**
   * Parses a String to match it with an ActionType.
   *
   * @param action A String that represents the action type.
   * @return An ActionType that matches the given string.
   */
  private static ActionFactory.ActionType parseActionType(String action) {
    return ActionFactory.ActionType.valueOf((action.toUpperCase()));
  }
}