package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class GreetingController {
    @Autowired
    private MessageSource messageSource;

    private static final String template = "%s, %s!";

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(
            @RequestHeader("Accept-Language") Locale locale,
            @RequestParam(value="name", defaultValue="World") String name
    ) {
        return new Greeting(String.format(template,messageSource.getMessage("HELLO",null,locale), name));
    }
}