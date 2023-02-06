package edu.ntnu.idatt2001;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/** 
 * A story is a collection of passages. It contains the title of the story,
 * a map of passages, with links as keys, and the opening passage for the given story.
 */ 
public class Story {
  private final String title;
  private Map<Link, Passage> passages;
  private final Passage openingPassage;
  
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
  }

  public String getTitle() {
    return title;
  }

  public Collection<Passage> getPassages() {
    
    List<Passage> passageList = new ArrayList<>();
    passages.forEach((key, value) -> passageList.add(Passage(value)));
    
    return passageList;
  }

  public Passage getOpeningPassage() {
    return openingPassage;
  }

  
}

