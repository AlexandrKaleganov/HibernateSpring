package ru.akaleganov.container.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akaleganov.service.RolesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * пока только получает список ролей, можно добавить добавление редактирование и удаление ролей
 */
@Controller
@Order(1)
public class ServletRoles {
    private final RolesService rolesService;
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    @Autowired
    public ServletRoles(RolesService rolesService) {
        this.rolesService = rolesService;
    }


    @RequestMapping(value = "listRoles", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(this.rolesService.findAll()));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
