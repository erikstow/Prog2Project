package edu.ntnu.idatt2001;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** 
 * A story is a collection of passages. It contains the title of the story,
 * a map of passages, with links as keys, and the opening passage for the given story.
 */ 
public class Story {
  private final String title;
  private final Map<Link, Passage> passages;
  private final Passage openingPassage;
  
  /** A Story is a collection of passages.
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

  public String getTitle() {
    return title;
  }

  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**  Method to get all passages in the story.
   *
   * @return A collection of all passages.
   */
  public Collection<Passage> getPassages() {
    return passages.values();
  }

  /** Method to get a passage from the story.
   *
   * @param link The link to the passage.
   *
   * @return The passage.
   */
  public Passage getPassage(Link link) throws NullPointerException {
    Objects.requireNonNull(link, "Link cannot be null");
    
    return passages.get(link);
  }

  /** Method to add a passage to the story.
   *
   * @param passage The passage to be added.
   */
  public void addPassage(Passage passage) throws NullPointerException {
    Objects.requireNonNull(passage, "Passage cannot be null");

    Link newLink = new Link(passage.getTitle(), passage.getTitle());
    passages.put(newLink, passage);
  }
}