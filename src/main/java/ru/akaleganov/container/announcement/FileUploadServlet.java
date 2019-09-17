package ru.akaleganov.container.announcement;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.ServiceAddObjects;

@MultipartConfig
@Controller
public class FileUploadServlet {
    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class);

    @GetMapping(value = "/upload")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json; charset=windows-1251");
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(req.getSession().getAttribute("phList")));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/upload")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("фото прилетели");
        if (ServletFileUpload.isMultipartContent(req)) {
            List<FileItem> multiparts = null;
            try {
                multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            assert multiparts != null;
            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    assert req.getSession().getAttribute("phList") instanceof ArrayList;
                    ServiceAddObjects.getInstance().
                            addPhoto((ArrayList<Photo>) req.getSession().getAttribute("phList"), item.getInputStream());
                }
            }
            this.doGet(req, resp);
        }

    }
}
