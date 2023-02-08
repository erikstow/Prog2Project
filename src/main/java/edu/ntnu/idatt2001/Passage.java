package edu.ntnu.idatt2001;

import java.util.List;

/** 
 * A passage is a part of a story. It contains the content of
 * the passage and a list of links. 
*/
public class Passage {
  
  private final String title;
  private final String content;
  private List<Link> links;
  
  /** Constructor for Passage.
   * @param title The title of the passage, must be unique.
   * @param content The content of the passage.
   */
  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public List<Link> getLinks() {
    return links;
  }

  /** Method to add a link to the list of links. 
   * @param link The link to be added
   * @return True if the link was added, false if it already existed
   */
  public boolean addLinks(Link link) { 
    boolean success = false;
    
    if (!links.contains(link)) {
      this.links.add(link);
      success = true;
    }
    return success;
  }

  /** Method to check if list has any links.
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((links == null) ? 0 : links.hashCode());
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
    if (title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!title.equals(other.title)) {
      return false;
    }
    if (content == null) {
      if (other.content != null) {
        return false;
      }
    } else if (!content.equals(other.content)) {
      return false;
    }
    if (links == null) {
      if (other.links != null) {
        return false;
      }
    } else if (!links.equals(other.links)) {
      return false;
    }
    return true;
  }
}
