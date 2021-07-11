package service;

import entity.Locality;

public interface LocalityDAO extends DAO<Locality> {

    Locality getByCityName(String cityName);
}
