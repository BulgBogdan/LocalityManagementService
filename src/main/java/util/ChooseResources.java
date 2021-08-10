package util;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseResources {

    private static Locale getLanguage(HttpServletRequest request) {
        String lang = request.getSession().getAttribute("lang").toString();
        if (lang.equals("ru")) {
            return new Locale("ru");
        } else {
            return new Locale("");
        }
    }

    public static String getMessageResource(HttpServletRequest request, String labelName) {
        ResourceBundle bundle = ResourceBundle.getBundle("language", ChooseResources.getLanguage(request));
        return String.valueOf(bundle.getObject(labelName));
    }
}
