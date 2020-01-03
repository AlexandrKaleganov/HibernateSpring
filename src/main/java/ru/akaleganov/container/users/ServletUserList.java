package ru.akaleganov.container.users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.domain.Users;
import ru.akaleganov.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ServletUserList {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);
    private final UsersService usersService;

    @Autowired
    public ServletUserList(UsersService usersService) {
        this.usersService = usersService;
    }

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
    protected ResponseEntity<List<Users>> findAll() {
        LOGGER.info("тянем список пользователей ");
        return ResponseEntity.ok().body(this.usersService.findAll());
    }

    @GetMapping(value = "users/findById/{id}")
    public String findById(ModelMap map, @PathVariable int id) {
        Users users = this.usersService.findByID(new Users(id));
        LOGGER.info(users);
        map.addAttribute("user", users);
        return "users/edit";
    }

    @GetMapping(value = "users/deleteUser/{id}")
    public String deleteUser(ModelMap map, @PathVariable int id) {
        map.addAttribute("user", this.usersService.delete(new Users(id)));
        return "redirect:api/users";
    }

    @PostMapping(value = "users/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> addUser(@RequestBody Users users, HttpServletRequest request) {
        LOGGER.error("добавить пользователя = " + users);
        Users data = this.usersService.add(users);
        request.setAttribute("users", data);
        return ResponseEntity.ok().body(data);
    }

    /**
     *
     * @param modelMap
     * @param users
     */
    @PutMapping(value = "users/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public void editUsers(ModelMap modelMap, @RequestBody Users users) {
        LOGGER.error("изменить пользователя = " + users);
        modelMap.put("users", this.usersService.edit(users));
    }
}
