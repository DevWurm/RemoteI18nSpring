package hello;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Locale;
import java.util.Optional;

public class RemoteMessageSource implements MessageSource {
    @Override
    public String getMessage(String s, Object[] objects, String s1, Locale locale) {
        try {
            String message = getMessage(s, objects, locale);

            if (message == null) return s1;

            return message;
        } catch (NoSuchMessageException e) {
            return s1;
        }
    }

    @Override
    public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
        Optional<Translation> translation = TranslationFetcher.fetch(locale);
        if (!translation.isPresent()) throw new NoSuchMessageException("NO TRANSLATION", locale);

        if (s.equals("HELLO")) {
            return translation.get().getHello();
        } else {
            throw new NoSuchMessageException(s, locale);
        }
    }

    @Override
    public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale)
            throws NoSuchMessageException {
        throw new NotImplementedException();
    }
}
