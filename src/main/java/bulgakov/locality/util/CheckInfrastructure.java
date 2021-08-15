package bulgakov.locality.util;

import bulgakov.locality.entity.Infrastructure;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class CheckInfrastructure {

    private static String nameInfr;
    private static int squareInfr;
    private static int floorsInfr;
    private static int personsInfr;

    public static boolean checkFullFields(HttpServletRequest request) {
        return !ObjectUtils.isEmpty(request.getParameter("name"))
                && !ObjectUtils.isEmpty(request.getParameter("square"))
                && !ObjectUtils.isEmpty(request.getParameter("floors"))
                && !ObjectUtils.isEmpty(request.getParameter("persons"));
    }

    public static Infrastructure checkInfrastructure(Infrastructure infrastructure, HttpServletRequest request) {
        if (checkFullFields(request)) {
            setAttributeInfrastructure(request);
            infrastructure.setName(nameInfr);
            infrastructure.setFloors(floorsInfr);
            infrastructure.setPersons(personsInfr);
            infrastructure.setSquare(squareInfr);
            return infrastructure;
        } else {
            return null;
        }
    }

    public static void setAttributeInfrastructure(HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(request.getParameter("name"))) {
            nameInfr = request.getParameter("name");
            request.setAttribute("name", nameInfr);
        }
        if (!ObjectUtils.isEmpty(request.getParameter("square"))) {
            squareInfr = Integer.parseInt(request.getParameter("square"));
            request.setAttribute("square", squareInfr);
        }
        if (!ObjectUtils.isEmpty(request.getParameter("floors"))) {
            floorsInfr = Integer.parseInt(request.getParameter("floors"));
            request.setAttribute("floors", floorsInfr);
        }
        if (!ObjectUtils.isEmpty(request.getParameter("persons"))) {
            personsInfr = Integer.parseInt(request.getParameter("persons"));
            request.setAttribute("persons", personsInfr);
        }
    }
}
