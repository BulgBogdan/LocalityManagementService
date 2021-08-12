package util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CheckConfirmData {

    public static String getAttributeParam(HttpServletRequest req) {
        if (Objects.nonNull(req.getParameter("confirmEdit"))) {
            return ChooseResources.getMessageResource(req, "label.confirmEdit");
        }
        if (Objects.nonNull(req.getParameter("confirmCreate"))) {
            return ChooseResources.getMessageResource(req, "label.confirmCreate");
        }
        if (Objects.nonNull(req.getParameter("confirmDelete"))) {
            return ChooseResources.getMessageResource(req, "label.confirmDelete");
        }
        return null;
    }
}
