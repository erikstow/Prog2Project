package edu.ntnu.idatt2001.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** 
 * A passage is a part of a story. It contains the content of
 * the passage and a list of links. 
*/
public class Passage {
  
  private final String title;
  private final String content;
  private final List<Link> links;
  
  /**
   * Constructor for Passage.
   *
   * @param title The title of the passage, must be unique.
   * @param content The content of the passage.
   */
  public Passage(String title, String content) throws NullPointerException {
    Objects.requireNonNull(title, "Title cannot be null");
    Objects.requireNonNull(content, "Content cannot be null");
    
    this.title = title;
    this.content = content;
    this.links = new ArrayList<>();
  }

  /**
   * Method to get the title of the passage.
   *
   * @return The title of the passage.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Method to get the content of the passage.
   *
   * @return The content of the passage.
   */
  public String getContent() {
    return content;
  }

  /**
   * Method to get the list of links.
   *
   * @return The list of links.
   */
  public List<Link> getLinks() {
    return links;
  }

  /** 
   * Method to add a link to the list of links.
   *
   * @param link The link to be added.
   *
   * @return True if the link was added, false if it already existed
   */
  public boolean addLink(Link link) throws NullPointerException {
    Objects.requireNonNull(link, "Link cannot be null");
    
    boolean success = false;
    if (!links.contains(link)) {
      this.links.add(link);
      success = true;
    }
    return success;
  }

  /**
   * Method to check if list has any links.
   *
   * @return True if list has any links, false if its empty.
   */
  public boolean hasLinks() {
    return !links.isEmpty();
  }

  @Override
  public String toString() {
    return "Passage [title=" + title + ", content=" + content + ", links=" + links + "]";
  }

  public String getAsString() {
    StringBuilder sb = new StringBuilder();
    sb.append("::").append(this.getTitle()).append('\n');
    sb.append(this.getContent()).append('\n');
    for (Link link : getLinks()) {
      sb.append(link.getAsString());
    }

    return sb.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + title.hashCode();
    result = prime * result + content.hashCode();
    result = prime * result + links.hashCode();
    return result;
  } 

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Passage other = (Passage) obj;
    if (!title.equals(other.title)) {
      return false;
    }
    if (!content.equals(other.content)) {
      return false;
    }
    return links.equals(other.links);
  }
}
