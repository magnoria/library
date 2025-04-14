package booklibrary.service;

import booklibrary.model.entity.ReviewEntity;
import booklibrary.model.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        //암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPwd = passwordEncoder.encode(reviewEntity.getRpwd());
        reviewEntity.setRpwd(hashedPwd);

        // 여기 부분 비밀번호 비교가 없음 실패함
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
