package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.config.SpringTestConfig;
import ru.akaleganov.domain.Transmission;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.TransmissionService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: сервис трансмиссий")
@SpringBootTest(classes = SpringTestConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class TransmissionServiceTest {
    @Autowired
    private TransmissionService transmissionService;

    @Test
    @DisplayName("тестирование: получить список трансмиссий")
    public void allTransmission() {
        String jsonTransmission = "{\"id\":\"1\", \"name\":\"РОБОТ\"}";
        Transmission transmission = this.transmissionService.
                add(new ServiceAddObjects().addTransmission(jsonTransmission));
        ArrayList<Transmission> actual = (ArrayList<Transmission>) this.transmissionService.findAll();
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "РОБОТ"));
        assertThat(actual.get(0).getName(), Is.is(expected.get(0)));
    }
}