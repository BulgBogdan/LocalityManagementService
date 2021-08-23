package bulgakov.locality.util;

public class CheckConfirmData {

    public static String getAttributeParam(String editData, String lang) {
        if (editData.equals("confirmEdit")) {
            return ChooseResources.getMessageResource(lang, "label.confirmEdit");
        }
        if (editData.equals("confirmCreate")) {
            return ChooseResources.getMessageResource(lang, "label.confirmCreate");
        }
        if (editData.equals("confirmDelete")) {
            return ChooseResources.getMessageResource(lang, "label.confirmDelete");
        }
        return null;
    }
}
