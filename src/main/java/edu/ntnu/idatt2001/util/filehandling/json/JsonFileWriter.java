package edu.ntnu.idatt2001.util.filehandling.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.util.filehandling.text.StoryReader;

import java.io.IOException;

public class JsonFileWriter {
  public class LinkKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      ObjectMapper mapper = (ObjectMapper) ctxt.getParser().getCodec();
      return mapper.readValue(key, Link.class);
    }
  }

  public Story deserializeStory() {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addKeyDeserializer(Link.class, new LinkKeyDeserializer());
    objectMapper.registerModule(module);
    Story story = null;
    try {
      story = StoryReader.read("Story title.paths");
      String jsonString = objectMapper.writeValueAsString(story);
      System.out.println(jsonString);

      story = objectMapper.readValue(jsonString, Story.class);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return story;
  }

  public static void main(String[] args) {
    JsonFileWriter writer = new JsonFileWriter();
    System.out.println(writer.deserializeStory());
  }
}
