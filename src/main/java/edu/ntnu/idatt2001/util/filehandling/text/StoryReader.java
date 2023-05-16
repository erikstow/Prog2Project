package edu.ntnu.idatt2001.util.filehandling.text;

import edu.ntnu.idatt2001.model.actions.ActionFactory;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.actions.Action;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StoryReader {
  private static final Pattern PASSAGE_PATTERN = Pattern.compile("::.*");
  private static final Pattern LINK_PATTERN = Pattern.compile("\\[.*");
  private static final Pattern LINK_TEXT_PATTERN = Pattern.compile("\\[(.*?)]");
  private static final Pattern LINK_REFERENCE_PATTERN = Pattern.compile("\\((.*?)\\)");
  private static final Pattern ACTION_PATTERN = Pattern.compile("!.*");
  private static final String ACTION_DELIMITER = ":";

  private StoryReader() {
    // Empty
  }

  public static Story read(String path) throws IOException{
    Story story;

    try ( Scanner scanner = new Scanner(new File(path))) {
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

  private static Passage readPassage(Scanner scanner) {
    String title = scanner.nextLine();
    title = title.substring(2);
    String content = scanner.nextLine();
    Passage passage = new Passage(title, content);

    while(scanner.hasNext(LINK_PATTERN)) {
      Link link = readLink(scanner);
      passage.addLink(link);
    }

    return passage;
  }

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

  private static Action readAction(Scanner scanner) {
    String line = scanner.nextLine();
    line = line.substring(1);
    String[] actionComponents = line.split(ACTION_DELIMITER);
    ActionFactory.ActionType actionType = parseActionType(actionComponents[0]);
    String actionValue = actionComponents[1];

    return ActionFactory.get(actionType, actionValue);
  }

  private static ActionFactory.ActionType parseActionType(String action) {
    return ActionFactory.ActionType.valueOf((action.toUpperCase()));
  }
}