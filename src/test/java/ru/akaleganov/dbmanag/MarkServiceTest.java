package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.config.SpringTestConfig;
import ru.akaleganov.domain.Mark;
import ru.akaleganov.service.MarkService;
import ru.akaleganov.service.ServiceAddObjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("тестирование: сервис марка")
@SpringBootTest(classes = SpringTestConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class MarkServiceTest {
    private String jsonMark = "{\"id\":\"\", \"name\":\"Toyota\"}";
    @Autowired
    private MarkService markService;

    @Test
    @DisplayName("тестирование: получить список марок авто")
    public void findAll() {
        Mark mark = this.markService.add(new ServiceAddObjects().addMark(jsonMark));
        assertTrue(mark.getId() > 0);
    }

    @Test
    @DisplayName("тестирование: получить марку авто по id")
    public void findByID() {
        Mark mark = this.markService.add(new ServiceAddObjects().addMark(jsonMark));
        assertThat(this.markService.findByID(mark).getName(), Is.is("Toyota"));
    }
}