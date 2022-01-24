package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtils {

  /**
   * Converts a JSON string to an object
   *
   * @param json
   * @param clazz
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return (T) mapper.readValue(json, clazz);
  }

  /**
   * Converts object to a JSON string
   *
   * @param object
   * @param prettyJson
   * @param includeNullValues
   * @return
   * @throws JsonProcessingException
   */
  public static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    if (!includeNullValues) {
      mapper.setSerializationInclusion(Include.NON_NULL);
    }
    if (prettyJson) {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    return mapper.writeValueAsString(object);
  }
}
