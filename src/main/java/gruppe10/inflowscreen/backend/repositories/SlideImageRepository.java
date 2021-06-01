package gruppe10.inflowscreen.backend.repositories;

import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public interface SlideImageRepository extends JpaRepository<SlideImage, Integer> {

    //     @Query("SELECT slide FROM Slide slide JOIN slide.organisations org WHERE org.id = ?1")
    //     @Query("DELETE FROM Slide slide WHERE slide.id = ?1")

    @Query("SELECT slideImage FROM SlideImage slideImage JOIN slideImage.slide s WHERE s.id = ?1")
    Optional<Set<SlideImage>> findBySlideId(int slideId);

    @Query("SELECT slideImage.id FROM SlideImage slideImage JOIN slideImage.slide s WHERE s.id = ?1")
    Optional<ArrayList<Integer>> findImgIdsBySlideId(int slideId);


    @Transactional
    @Modifying
    @Query("DELETE FROM SlideImage slideImage WHERE slideImage.id = ?1")
    void deleteSlideImageById(int slideImageId);

}
