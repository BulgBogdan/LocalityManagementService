package bulgakov.locality.util;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.entity.Locality;
import org.apache.commons.lang3.StringUtils;

public class CheckField {

    public static boolean checkFullFieldsInfrastructure(Infrastructure infrastructure) {
        return StringUtils.isNotBlank(infrastructure.getName())
                && infrastructure.getFloors() > 0
                && infrastructure.getPersons() > 0
                && infrastructure.getSquare() > 0;
    }

    public static boolean checkFullFieldsLocality(Locality locality) {
        return StringUtils.isNotBlank(locality.getName())
                && locality.getPopulation() > 0;
    }
}
