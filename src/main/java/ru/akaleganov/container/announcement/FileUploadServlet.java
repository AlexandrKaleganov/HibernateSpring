package ru.akaleganov.container.announcement;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.akaleganov.domain.Photo;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@Controller
public class FileUploadServlet {
    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class);

    @GetMapping(value = "/upload")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/json; charset=windows-1251");
        if (req.getSession().getAttribute("phList") == null) {
            req.getSession().setAttribute("phList", new ArrayList<Photo>());
        }
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(req.getSession().getAttribute("phList")));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/upload")
    protected ResponseEntity<List<Photo>> doPost(@RequestParam("file") MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("фото прилетели");
        if (req.getSession().getAttribute("phList") == null) {
            req.getSession().setAttribute("phList", new ArrayList<Photo>());
        }
        assert req.getSession().getAttribute("phList") instanceof ArrayList;
        ServiceAddObjects.getInstance().
                addPhoto((ArrayList<Photo>) req.getSession().getAttribute("phList"), file.getInputStream());
        return ResponseEntity.ok().body((ArrayList<Photo>) req.getSession().getAttribute("phList"));
    }
}
