package booklibrary.service;

import booklibrary.model.dto.BookDto;
import booklibrary.model.dto.ReviewDto;
import booklibrary.model.entity.BookEntity;
import booklibrary.model.entity.ReviewEntity;
import booklibrary.model.repository.BookRepository;
import booklibrary.model.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    // 리뷰 등록 { "rname" : "쿠키 " ,"rcontents" : "안녕하세요" ,"rpwd" : "123" , "bno" : "2"}
    public boolean onReview(ReviewDto reviewDto){
        // 1. Dto에서 bno 추출
        int bookBno = reviewDto.getBno();

        //2. BookRepository를 사용하여 해당 bno의 bookEntity 조회
        Optional<BookEntity> optionalBook = bookRepository.findById(bookBno);

        if (optionalBook.isPresent()){
            BookEntity book = optionalBook.get();

            //암호화
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPwd = passwordEncoder.encode(reviewDto.getRpwd());

            // 4. ReviewEntity 생성 및 BookEntity 연관 설정
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .rcontents(reviewDto.getRcontents())
                    .rname(reviewDto.getRname())
                    .rpwd(hashedPwd)
                    .book(book) // BookEntity 설정
                    .build();

            // 5. ReviewRepository를 사용하여 ReviewEntity 저장
            ReviewEntity savedReview = reviewRepository.save(reviewEntity);
            return savedReview.getRno() >= 1;
        }else {  // 해당 bno의 책이 존재하지 않는 경우 예외 처리 (false 반환 또는 예외 던지기)
            System.out.println("존재하지 않는 책 번호입니다: " + bookBno);
            return false;}
    }

    // 리뷰 삭제 // 비밀번호 필요

    public boolean deleteReview(ReviewEntity reviewEntity){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findById(reviewEntity.getRno());
        ReviewEntity entity = optionalReviewEntity.get();
        if (reviewEntity.getRno() == entity.getRno()){
            if (bCryptPasswordEncoder.matches(reviewEntity.getRpwd() , entity.getRpwd())) {
                reviewRepository.deleteById(reviewEntity.getRno());
                return true;
            }
        }
        return false;
    }

    // 리뷰 조회


    public List<ReviewDto> reviewFindAll(int bno) {
        BookEntity book = bookRepository.findById(bno).orElse(null);
                if (book == null){return null;}

       List<ReviewEntity> reviewEntityList = book.getReviews();
       return  reviewEntityList.stream()
               .map((entity) -> entity.reviewDto())
               .collect(Collectors.toList());

    }
}
