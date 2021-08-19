package bulgakov.locality.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseResources {

    private static Locale getLanguage(String lang) {
        if (lang.equals("ru")) {
            return new Locale("ru");
        } else {
            return new Locale("");
        }
    }

    public static String getMessageResource(String lang, String labelName) {
        ResourceBundle bundle = ResourceBundle.getBundle("language", ChooseResources.getLanguage(lang));
        return String.valueOf(bundle.getObject(labelName));
    }
}
