package cl.javier.starwars.service;
import tools.jackson.databind.JsonNode; import org.springframework.beans.factory.annotation.Value; import org.springframework.http.client.JdkClientHttpRequestFactory; import org.springframework.stereotype.Component; import org.springframework.web.client.RestClient; import java.net.http.HttpClient; import java.time.Duration; import java.util.*;
@Component public class SwapiClient{
 private final RestClient client;
 public SwapiClient(@Value("${app.swapi.base-url}") String base,@Value("${app.swapi.timeout-seconds}") int seconds){var http=HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(seconds)).build();var factory=new JdkClientHttpRequestFactory(http);factory.setReadTimeout(Duration.ofSeconds(seconds));this.client=RestClient.builder().baseUrl(base).requestFactory(factory).build();}
 public List<JsonNode> allPeople(){List<JsonNode> out=new ArrayList<>();String url="/people/";while(url!=null){JsonNode page=get(url);page.path("results").forEach(out::add);url=page.path("next").isNull()?null:page.path("next").asText(null);}return out;}
 public JsonNode get(String url){return client.get().uri(url).retrieve().body(JsonNode.class);}
 public static long id(String url){String s=url.replaceAll("/+$","");return Long.parseLong(s.substring(s.lastIndexOf('/')+1));}
}
