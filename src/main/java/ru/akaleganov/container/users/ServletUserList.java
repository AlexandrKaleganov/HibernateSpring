package ru.akaleganov.container.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.UserDispatch;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/api")
public class ServletUserList {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    /**
     * переход на страницу с пользователями
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    protected String doGet() {
        return "users/userList";
    }

    /**
     * @return получение списка всех пользователей
     */
    @GetMapping(value = "users/list")
    protected ResponseEntity<ArrayList<Users>> findAll() {
        LOGGER.info("тянем список пользователей ");
        return ResponseEntity.ok().body(UserDispatch.getInstance().access("getListUser", new Users()));
    }

    @GetMapping(value = "users/findById/{id}")
    public String findById(ModelMap map, @PathVariable int id) {
        Users users = UserDispatch.getInstance().access("findByIdUser", new Users(id));
        LOGGER.info(users);
        map.addAttribute("user", users);
        return "users/edit";
    }

    @GetMapping(value = "users/deleteUser/{id}")
    public String deleteUser(ModelMap map, @PathVariable int id) {
        map.addAttribute("user", UserDispatch.getInstance().access("deleteUser", new Users(id)));
        return "redirect:api/users";
    }

    @PostMapping(value = "users/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> addUser(@RequestBody Users users, HttpServletRequest request) {
        LOGGER.error("добавить пользователя = " + users);
        Users data = UserDispatch.getInstance().access("addUser", users);
        request.setAttribute("users", data);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping(value = "users/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users editUsers(ModelMap modelMap, @RequestBody Users users) {
        LOGGER.error("изменить пользователя = " + users);
        modelMap.put("users", UserDispatch.getInstance().access("addOrUpdate", users));
        return UserDispatch.getInstance().access("addOrUpdate", users);
    }
}
