package ru.akaleganov.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Announcement;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Kaleganov Alexander
 * @version 0.0.1
 * @project HibernateSpring
 * @since 03 янв. 20
 */
@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    List<Announcement> findAllByName(String name);

    @Query("from Announcement where created  >= :date")
    List<Announcement> findAllByCreatedTodayOrLater(Timestamp date);

    @Query("from Announcement where size(car.photo) > 0")
    List<Announcement> findAllByCarPhotoEmptyPhoto();
    @Query("from Announcement where car.model.mark.id = :markId")
    List<Announcement> findAllByCarMark(Long markId);
}
