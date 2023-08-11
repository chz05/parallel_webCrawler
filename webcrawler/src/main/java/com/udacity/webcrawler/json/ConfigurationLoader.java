package com.udacity.webcrawler.json;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() {
    // TODO: Fill in this method.
    Reader stringReader = null;
    try {
      String jsonString = Files.readString(path);
      stringReader = new StringReader(jsonString);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return read(stringReader);
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  public static CrawlerConfiguration read(Reader reader){
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(reader);
    // TODO: Fill in this method
    ObjectMapper objectMapper = new ObjectMapper();
    CrawlerConfiguration crawlerConfiguration = null;
    try {
      crawlerConfiguration = objectMapper.readValue(reader, CrawlerConfiguration.class);
    }catch (IOException e){
      e.printStackTrace();
    }finally {
      try {
        //objectMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        reader.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    //return new CrawlerConfiguration.Builder().build();
    return crawlerConfiguration;
  }
}
