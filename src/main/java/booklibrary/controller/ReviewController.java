package booklibrary.controller;

import booklibrary.model.entity.ReviewEntity;
import booklibrary.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 등록 {"rcontents" : "제목" , "rpwd" : "123"}
    @PostMapping
    public boolean onReview(@RequestBody ReviewEntity reviewEntity){

       return reviewService.onReview(reviewEntity);
    }

    // 리뷰 삭제 // 비밀번호 필요 // {"rno" : "1" , "rcontents" : "제목" , "rpwd" : "123"}
    @PostMapping("/delete")
    public boolean deleteReview(@RequestBody ReviewEntity reviewEntity){
        boolean result = reviewService.deleteReview(reviewEntity);
       return result;
    }

    // 리뷰 전체 조회
    @GetMapping
    public List<ReviewEntity> reviewList(){

        return reviewService.reviewList();
    }
}
