package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Announcement;
import ru.akaleganov.domain.Mark;
import ru.akaleganov.repository.AnnouncementRepository;
import ru.akaleganov.repository.CarRepository;
import ru.akaleganov.repository.PhotoRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementService implements Store<Announcement> {
    private final AnnouncementRepository announcementRepository;
    private final PhotoRepository photoRepository;
    private final CarRepository carRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository, PhotoRepository photoRepository, CarRepository carRepository
    ) {
        this.announcementRepository = announcementRepository;
        this.photoRepository = photoRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Announcement add(Announcement announcement) {
        return this.announcementRepository.save(announcement);
    }

    @Override
    @Transactional
    public Announcement delete(Announcement announcement) {
        this.photoRepository.deleteAllByCarId(announcement.getCar().getId());
        this.carRepository.deleteCarByAnnouncementId(announcement.getId());
        this.announcementRepository.deleteById(announcement.getId());
        return announcement;
    }

    @Override
    public Announcement edit(Announcement announcement) {
        return this.announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> findAll() {
        return (List<Announcement>) this.announcementRepository.findAll();
    }

    @Override
    public Announcement findByID(Announcement announcement) {
        return this.announcementRepository.findById(announcement.getId()).orElse(new Announcement());
    }

    @Override
    public List<Announcement> findByName(Announcement announcement) {
        return this.announcementRepository.findAllByName(announcement.getName());
    }

    /**
     * @return получить список объявлений за последний день
     */
    public List<Announcement> toShowForTheLastDay() {
        LocalDate date = LocalDate.now();
        LOGGER.info(date);
        Date date1 = new Date();
        Timestamp timestamp = Timestamp.valueOf(LocalDate.now().toString() + " 00:00:00");
        return this.announcementRepository.findAllByCreatedTodayOrLater(timestamp);
    }

    /**
     * @return получить список объявлений с фотографиями
     */
    public List<Announcement> toShowWithAPhoto() {
        return this.announcementRepository.findAllByCarPhotoEmptyPhoto();
    }

    /**
     * @return получить список объявлений с определённой марки
     */
    public List<Announcement> toShowACertainBrand(Mark mark) {
        LOGGER.debug("mark, по которой будем искать список объявления = " + mark.getId());
        return this.announcementRepository.findAllByCarMark(mark.getId());
    }

    @Override
    public Announcement findByLoginPass(Announcement announcement) {
        error();
        return null;
    }

    @Override
    public Announcement findByLogin(Announcement announcement) {
        error();
        return null;
    }
}
