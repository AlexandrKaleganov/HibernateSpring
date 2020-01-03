package ru.akaleganov.container.announcement.car;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akaleganov.domain.Transmission;
import ru.akaleganov.service.TransmissionService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Controller
public class TransmissionServlet {
    private static final Logger LOGGER = Logger.getLogger(TransmissionServlet.class);
    private final TransmissionService transmissionService;

    public TransmissionServlet(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }


    @GetMapping(value = "/transmission")
    protected ResponseEntity<List<Transmission>> getTransmissiont() throws ServletException, IOException {
//        try {
//            PrintWriter writer = new PrintWriter(resp.getOutputStream());
//            writer.append(new ObjectMapper().writeValueAsString(this.transmissionService.findAll()));
//            writer.flush();
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
       return ResponseEntity.ok().body(this.transmissionService.findAll());
    }


}
