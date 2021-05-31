package gruppe10.inflowscreen.backend.repositories;

import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import gruppe10.inflowscreen.backend.models.entities.TextBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public interface TextBoxRepository extends JpaRepository<TextBox, Integer> {


    @Query("SELECT textBox FROM TextBox textBox JOIN textBox.slide s WHERE s.id = ?1")
    Optional<Set<TextBox>> findBySlideId(int slideId);

    @Query("SELECT textBox.id FROM TextBox textBox JOIN textBox.slide s WHERE s.id = ?1")
    Optional<ArrayList<Integer>> findTextBoxIdsBySlideId(int slideId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TextBox textBox WHERE textBox.id = ?1")
    void deleteTextBoxById(int textBoxId);


}
