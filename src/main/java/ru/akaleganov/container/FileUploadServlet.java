package ru.akaleganov.container;

import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("fileimg") + "вывод файл");
        ArrayList<String> photolist = new ArrayList<>();
        photolist.add(req.getParameter("fileimg"));
        ArrayList<Photo> photos = (ArrayList<Photo>) ServiceAddObjects.getInstance().addPhoto(photolist);
        photos.forEach(System.out::println);
    }
}
