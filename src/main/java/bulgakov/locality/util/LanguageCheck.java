package bulgakov.locality.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class LanguageCheck {

    public static String getLanguageSession(HttpServletRequest request) {
        if (StringUtils.isBlank((String) request.getSession().getAttribute("lang"))) {
            return String.valueOf(request.getSession().getAttribute("language")).substring(0, 2);
        } else {
            return String.valueOf(request.getSession().getAttribute("lang"));
        }
    }
}
