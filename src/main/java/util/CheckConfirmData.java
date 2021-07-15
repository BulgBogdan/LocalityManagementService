package util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CheckConfirmData {

    public static String getAttributeParam(HttpServletRequest req) {
        if (Objects.nonNull(req.getParameter("confirmEdit"))) {
            return "Данные успешно изменены";
        }
        if (Objects.nonNull(req.getParameter("confirmCreate"))) {
            return "Данные успешно добавлены";
        }
        if (Objects.nonNull(req.getParameter("confirmDelete"))) {
            return "Данные успешно удалены";
        }
        return null;
    }
}
