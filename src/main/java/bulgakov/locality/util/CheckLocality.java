package bulgakov.locality.util;

import bulgakov.locality.entity.Locality;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class CheckLocality {

    private static String name;
    private static int population;

    public static boolean checkFullFields(HttpServletRequest request) {
        return !ObjectUtils.isEmpty(request.getParameter("name"))
                && !ObjectUtils.isEmpty(request.getParameter("population"));
    }

    public static Locality checkLocality(Locality locality, HttpServletRequest request) {
        if (checkFullFields(request)) {
            setAttributeLocality(request);
            locality.setName(name);
            locality.setPopulation(population);
            return locality;
        } else {
            return null;
        }
    }

    public static void setAttributeLocality(HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(request.getParameter("name"))) {
            name = request.getParameter("name");
            request.setAttribute("name", name);
        }
        if (ObjectUtils.isEmpty(request.getParameter("population"))) {
            population = Integer.parseInt(request.getParameter("population"));
            request.setAttribute("population", population);
        }
    }
}
