package bulgakov.locality.util;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class LanguageCheck {

    public static String getLanguageSession(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(request.getSession().getAttribute("lang"))) {
            return String.valueOf(request.getSession().getAttribute("language")).substring(0, 2);
        } else {
            return String.valueOf(request.getSession().getAttribute("lang"));
        }
    }
}
