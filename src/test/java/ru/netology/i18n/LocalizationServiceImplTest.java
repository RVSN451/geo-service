package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    @Test
    public void localizationServiceReturnRussianLanguage_isCorrect(){
        Country country = Country.RUSSIA;
        LocalizationService ls = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String actual = ls.locale(country);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void localizationServiceReturnEnglishLanguage_isCorrect(){
        Country country = Country.GERMANY;
        LocalizationService ls = new LocalizationServiceImpl();
        String expected = "Welcome";
        String actual = ls.locale(country);

        Assertions.assertEquals(expected, actual);
    }
}