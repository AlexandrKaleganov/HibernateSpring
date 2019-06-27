package ru.akaleganov.container;


import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.ServiceAddObjects;

@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class);

    @Override
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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String UPLOAD_DIRECTORY = "d:/uploads";

        if (ServletFileUpload.isMultipartContent(req)) {
            List<FileItem> multiparts = null;
            try {
                multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            System.out.println(multiparts.size());
            for (FileItem item : multiparts) {
                System.out.println(item);
                if (!item.isFormField()) {
                    ArrayList<Photo> photos = ServiceAddObjects.getInstance().
                            addPhoto((ArrayList<Photo>) req.getSession().getAttribute("phList"), item.getInputStream());
                }
            }
            this.doGet(req, resp);
        }

    }
}
