package booklibrary.service;

import booklibrary.model.BookEntity;
import booklibrary.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //1. 책 등록

    public boolean post( BookEntity bookEntity){

       BookEntity saveEntity = bookRepository.save(bookEntity);
       return true;
    }

    //2. 책 개별 수정

    public boolean put( BookEntity bookEntity){
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookEntity.getBno());

        if (optionalBookEntity.isPresent()){
            BookEntity entity = optionalBookEntity.get();
            // 비밀번호 비교
            if (bookEntity.getBpwd().equals(entity.getBpwd()) ){
                entity.setBtitle(bookEntity.getBtitle());
                entity.setBwriter(bookEntity.getBwriter());
                entity.setBpwd(bookEntity.getBpwd());
                entity.setBcomments(bookEntity.getBcomments());
                return true;
            }

        }
        return false;
    }

    //3. 책 개별 삭제

    public boolean delete(BookEntity bookEntity){
       Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookEntity.getBno());
        System.out.println("optionalBookEntity = " + optionalBookEntity);
       BookEntity entity = optionalBookEntity.get();
        System.out.println("entity = " + entity);
        System.out.println(bookEntity.getBpwd());
        System.out.println(entity.getBpwd());

       if (bookEntity.getBpwd().equals(entity.getBpwd())){
           bookRepository.deleteById(bookEntity.getBno());
           return true;
       }
       return false;
    }

    //4.책 추천 목록조회
    List<BookEntity> list;
    public List<BookEntity> findAll(){
       List<BookEntity> list = bookRepository.findAll();
       return list;
    }

    //5. 책 개별 조회
    public BookEntity findOne(int bno){
        return bookRepository.findById(bno).orElse(null);
    }



}
