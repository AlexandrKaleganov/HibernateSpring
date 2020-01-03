package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.config.SpringTestConfig;
import ru.akaleganov.domain.Roles;
import ru.akaleganov.service.RolesService;
import ru.akaleganov.service.ServiceAddObjects;

import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: сервис ролей")
@SpringBootTest(classes = SpringTestConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class RolesServiceTest {
    private final static Logger LOGGER = getLogger(RolesServiceTest.class);

    @Autowired
    private RolesService rolesService;

    @Test
    @DisplayName("тестирование: получить список ролей")
    public void findAll() {
        LOGGER.error(rolesService.toString());
        String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";
        Roles roles = rolesService.add(new ServiceAddObjects().addRole(jsonRole));
        assertThat(rolesService.findByID(roles).getRole(), Is.is("ADMIN"));
    }
}