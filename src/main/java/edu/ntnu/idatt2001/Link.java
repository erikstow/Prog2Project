package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.actions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A link makes it possible to move from one passage to another. Links bind the different
 * parts of a story.
 */
public class Link {
  private final String text;
  private final String reference;
  private final List<Action> actions;

  /**
   * Constructing a Link with a descriptive text and reference to a passage,
   * with an empty action list.
   *
   * @param text text indicating a choice or action in a story.
   *             Visible to the player.
   * @param reference unique identifier to a passage. Usually the title of the passage.
   */
  public Link(String text, String reference) throws IllegalArgumentException {
    if (text == null || reference == null) {
      throw new IllegalArgumentException("Text and refference cannot be empty");
    }
    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
  }

  public String getReference() {
    return reference;
  }

  public List<Action> getActions() {
    return this.actions;
  }

  public String getText() {
    return text;
  }

  public void addAction(Action action) {
    this.actions.add(action);
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
