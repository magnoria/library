package booklibrary.service;

import booklibrary.model.ReviewEntity;
import booklibrary.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 리뷰 등록 {"rcontents" : "안녕하세요" ,  "rpwd" : "123" , "bno" : "2"}
    public boolean onReview(ReviewEntity reviewEntity){

        ReviewEntity reviewSave = reviewRepository.save(reviewEntity);
        return false;
    }

    // 리뷰 삭제 // 비밀번호 필요

    public boolean deleteReview(ReviewEntity reviewEntity){
        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findById(reviewEntity.getRno());
        ReviewEntity entity = optionalReviewEntity.get();
        if (reviewEntity.getRno() == entity.getRno()){
            reviewRepository.deleteById(reviewEntity.getRno());
            return true;
        }
        return false;
    }

    // 리뷰 전체 조회

    public List<ReviewEntity> reviewList(){
        return reviewRepository.findAll();
    }
}
