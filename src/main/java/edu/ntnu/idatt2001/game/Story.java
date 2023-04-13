package edu.ntnu.idatt2001.game;

import java.util.*;

/** 
 * A story is a collection of passages. It contains the title of the story,
 * a map of passages, with links as keys, and the opening passage for the given story.
 */
public class Story {
  private final String title;
  private final Map<Link, Passage> passages;
  private final Passage openingPassage;
  
  /**
   *A Story is a collection of passages.
   *
   * @param title Title of the story.
   * @param openingPassage OpeningPassage of the story.
   */
  public Story(String title, Passage openingPassage) throws NullPointerException {
    Objects.requireNonNull(title, "Title cannot be null");
    Objects.requireNonNull(openingPassage, "Opening passage cannot be null");
   
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
    this.addPassage(openingPassage);
  }

  /**
   * Method to get the title of the story.
   *
   * @return The title of the story.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Method to get the opening passage of the story.
   *
   * @return The opening passage of the story.
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * Method to get all passages in the story.
   *
   * @return A collection of all passages.
   */
  public Collection<Passage> getPassages() {
    return passages.values();
  }

  /**
   * Method to get a passage from the story.
   *
   * @param link The link to the passage.
   *
   * @return The passage.
   */
  public Passage getPassage(Link link) throws NullPointerException {
    Objects.requireNonNull(link, "Link cannot be null");
    return passages.get(new Link(link.getReference(), link.getReference()));
  }

  /**
   * Method to add a passage to the story.
   *
   * @param passage The passage to be added.
   */
  public void addPassage(Passage passage) throws NullPointerException {
    Objects.requireNonNull(passage, "Passage cannot be null");

    Link newLink = new Link(passage.getTitle(), passage.getTitle());
    passages.put(newLink, passage);
  }

  /**
   * Returns a list of all broken links. A link is broken if it is in a passage, but
   * doesn't lead to an exsisting passage.
   *
   * @return list of broken links
   */
  public List<Link> getBrokenLinks() {
    return this.getPassages().stream()
      .flatMap(p -> p.getLinks().stream())
      //.filter(passages::containsKey)
      .filter(link -> this.getPassage(link) == null)
      .toList();
  }

  /**
   * Removes a passage given a link if no other passages leads to it.
   *
   * @param link link to the passage to be deleted
   */
  public void removePassage(Link link) {
    Objects.requireNonNull(link);

    Passage passage = this.getPassage(link);
    if (passage == null) {
      return;
    }

    if (this.getPassages().stream()
        .flatMap(p -> p.getLinks().stream())
        .noneMatch(l -> l.getReference().equals(passage.getTitle()))) {
      this.passages.remove(new Link(passage.getTitle(), passage.getTitle()));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Story story = (Story) o;
    return Objects.equals(title, story.title) && Objects.equals(passages, story.passages) && Objects.equals(openingPassage, story.openingPassage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, passages, openingPassage);
  }

  /**
   * Returns a string compatible with the file format for storing a story.
   *
   * @return story in string form
   */
  public String getAsString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getTitle()).append('\n');
    sb.append('\n');
    for (Passage passage : passages.values()) {
      sb.append(passage.getAsString());
      sb.append('\n');
    }
    return sb.toString();
  }
}