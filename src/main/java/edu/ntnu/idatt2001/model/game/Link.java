package edu.ntnu.idatt2001.model.game;

import edu.ntnu.idatt2001.model.actions.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A link makes it possible to move from one passage to another. Links bind the different
 * parts/passages of a story together. A link has a descriptive text, a reference to a passage,
 * and a list of actions.
 */
public class Link {
  private final String text;
  private final String reference;
  private final List<Action> actions;

  /**
   * Constructing a Link with a descriptive text and reference to a passage,
   * with an empty action list. Text is the text that is displayed to the user.
   *
   * @param text text indicating a choice or action in a story.
   *             
   * @param reference unique identifier to a passage. Usually the title of the passage.
   */
  public Link(String text, String reference) throws NullPointerException {
    Objects.requireNonNull(text, "Text cannot be empty");
    Objects.requireNonNull(reference, "Reference cannot be empty");

    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
  }

  /**
   * Method that returns the reference.
   *
   * @return the reference.
   */
  public String getReference() {
    return reference;
  }

  /**
   * Method that returns the list of actions.
   *
   * @return the list of actions.
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Method that returns the text of the link.
   *
   * @return the text of the link.
   */
  public String getText() {
    return text;
  }

  /** 
   * Method that adds an action to the list of actions.
   *
   * @param action the action to be added.
   */
  public void addAction(Action action) throws NullPointerException {
    Objects.requireNonNull(action, "Action cannot be null");
    this.actions.add(action);
  }

  public String getAsString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[")
      .append(getText())
      .append("](")
      .append(getReference())
      .append(")\n");
    for (Action action : this.actions) {
      sb.append(action.getAsString());
    }
    return sb.toString();
  }

  @Override
  public String toString() {
    return "Link{"
      +
      "text='" + text + '\''
      +
      ", reference='" + reference + '\''
      +
      ", actions=" + actions
      +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Link link = (Link) o;
    return Objects.equals(text, link.text)
      &&
      Objects.equals(reference, link.reference)
      &&
      Objects.equals(actions, link.actions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, reference, actions);
  }
}
