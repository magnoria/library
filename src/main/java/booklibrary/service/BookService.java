package booklibrary.service;

import booklibrary.model.dto.BookDto;
import booklibrary.model.entity.BookEntity;
import booklibrary.model.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //1. 책 등록

    public boolean post( BookDto bookDto){
        //암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPwd = passwordEncoder.encode(bookDto.getBpwd());
        bookDto.setBpwd(hashedPwd);

        BookEntity bookEntity = bookDto.toEntity();
        BookEntity saveEntity = bookRepository.save(bookEntity);
        if (saveEntity.getBno() >= 1){return true;}
        return false;
    }

    //2. 책 개별 수정

    public boolean put( BookDto bookDto){
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookDto.getBno());
        String password =
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

    public boolean delete(BookDto bookDto){
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
    List<BookDto> list;
    public List<BookEntity> findAll(){
       List<BookEntity> list = bookRepository.findAll();
       return list;
    }

    //5. 책 개별 조회
    public BookEntity findOne(int bno){
        return bookRepository.findById(bno).orElse(null);
    }



}
