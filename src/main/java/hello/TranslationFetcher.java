package hello;

import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Optional;

public class TranslationFetcher {
    private static final String urlTemplate = "http://localhost:4200/assets/some/path/i18n/%s.json";

    public static Optional<Translation> fetch(Locale locale) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Translation translation = restTemplate
                    .getForObject(String.format(urlTemplate, locale.getLanguage()), Translation.class);

            if (translation == null) {
                return Optional.empty();
            } else {
                return Optional.of(translation);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            return Optional.empty();
        }
    }
}
