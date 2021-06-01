package ru.netology.sender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class MessageSenderImplTest {

    @Test
    public void messageSenderReturnRussia(){
        Map<String, String> headers = new HashMap<String, String>();

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp(any(String.class)))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    public void messageSenderReturnUSA(){
        Map<String, String> headers = new HashMap<String, String>();

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp(any(String.class)))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        assertEquals("Welcome", messageSender.send(headers));
    }

    @Test
    public void messageSenderPassStringToGeoServiceByIp(){

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "55.123.12.19");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp(any(String.class)))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(any(Country.class)))
                .thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        messageSender.send(headers);

        Mockito.verify(geoService).byIp(captor.capture());
        Assertions.assertEquals("55.123.12.19", captor.getValue());
    }

    @Test
    public void messageSenderCallGeoServiceByIp(){

        Map<String, String> headers = new HashMap<String, String>();

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp(any(String.class)))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(any(Country.class)))
                .thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        messageSender.send(headers);

        Mockito.verify(geoService,Mockito.only());
    }
}