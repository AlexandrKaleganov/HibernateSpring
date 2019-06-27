package ru.akaleganov.container;


import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
    private String filePath, tempPath;
    private int maxFileSize = 5242880;
    private int maxMemSize = 5 * 1024;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file-upload");
        tempPath = getServletContext().getInitParameter("temp-upload");
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
//                        File fileSaveDir = new File(UPLOAD_DIRECTORY);
//                        if (!fileSaveDir.exists()) {
//                            fileSaveDir.mkdir();
//                        }
                        String name = new File(item.getName()).getName();
                        System.out.println(name);
//                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        Photo photo = ServiceAddObjects.getInstance().addPhoto(new File(item.getName()));
                        System.out.println(photo);
                    }
                }


            PrintWriter out = resp.getWriter();
            out.print("{\"status\":1}");
        }

    }
}
