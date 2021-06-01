package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    @Test
    public void geoServiceByIpReturnMoscow_isCorrect(){
        String inputIp = "172.023.321.00";
        GeoService geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        Location actual = geoService.byIp(inputIp);
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
    }

    @Test
    public void geoServiceByIpReturnNewYork_isCorrect(){
        String inputIp = "96.0187.21.00";
        GeoService geoService = new GeoServiceImpl();
        Location expected = new Location("New York", Country.USA, null,  0);
        Location actual = geoService.byIp(inputIp);
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
    }
}