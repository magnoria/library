package booklibrary.model.repository;

import booklibrary.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {



}
