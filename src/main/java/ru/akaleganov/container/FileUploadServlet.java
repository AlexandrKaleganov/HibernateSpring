package ru.akaleganov.container;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
@MultipartConfig()
public class FileUploadServlet extends HttpServlet {
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
        System.out.println("asdasdasdasdasssssssss");
        Part part = req.getPart("file");
        System.out.println("asdasdasdasdasssssssss");
        byte[] bytes = IOUtils.toByteArray(part.getInputStream());
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
    }
}
