package acme;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.util.LocaleHelper;


import java.util.Locale;

@Path("/")
public class GreetingResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance hello(String name, Integer age);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance hello(@QueryParam("lang") String lang) {
        Locale locale = lang != null ? LocaleHelper.extractLocale(lang) : Locale.ENGLISH;
        return Templates.hello("Quarkus", 2).setAttribute("locale", locale);
    }
}
