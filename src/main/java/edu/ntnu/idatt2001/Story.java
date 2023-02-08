package edu.ntnu.idatt2001;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** 
 * A story is a collection of passages. It contains the title of the story,
 * a map of passages, with links as keys, and the opening passage for the given story.
 */ 
public class Story {
  private final String title;
  private Map<Link, Passage> passages;
  private final Passage openingPassage;
  
  /** A Story is a collection of passages.
   * @param title Title of the story. 
   * @param openingPassage OpeningPassage of the story.
   */
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  public String getTitle() {
    return title;
  }

  public Passage getOpeningPassage() {
    return openingPassage;
  }

  public Collection<Passage> getPassages() {
    return passages.values();
  }

  public Passage getPassage(Link link) {
    return passages.get(link);
  }
  
  public void addPassage(Passage passage) {
    Link newLink = new Link(passage.getTitle(), passage.getTitle());
    passages.put(newLink, passage);
  }
}

