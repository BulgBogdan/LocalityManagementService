package bulgakov.locality.util;

public class CheckConfirmData {

    public static String getAttributeParam(String editData, String lang) {
        if (editData.equals("edited")) {
            return ChooseResources.getMessageResource(lang, "label.confirmEdit");
        }
        if (editData.equals("created")) {
            return ChooseResources.getMessageResource(lang, "label.confirmCreate");
        }
        if (editData.equals("deleted")) {
            return ChooseResources.getMessageResource(lang, "label.confirmDelete");
        }
        return null;
    }
}
