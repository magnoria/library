package booklibrary.service;

import booklibrary.model.dto.BookDto;
import booklibrary.model.entity.BookEntity;
import booklibrary.model.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (optionalBookEntity.isPresent()){
            BookEntity entity = optionalBookEntity.get();
            // 비밀번호 비교
            if (bCryptPasswordEncoder.matches(bookDto.getBpwd() , entity.getBpwd())){
                entity.setBtitle(bookDto.getBtitle());
                entity.setBwriter(bookDto.getBwriter());
                entity.setBpwd(entity.getBpwd());
                entity.setBcomments(bookDto.getBcomments());
                return true;
            }

        }
        return false;
    }

    //3. 책 개별 삭제

    public boolean delete(BookDto bookDto){
       Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookDto.getBno());
        System.out.println("optionalBookEntity = " + optionalBookEntity);
       BookEntity entity = optionalBookEntity.get();
        System.out.println("entity = " + entity);
        System.out.println(bookDto.getBpwd());
        System.out.println(entity.getBpwd());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

       if (bCryptPasswordEncoder.matches(bookDto.getBpwd(), entity.getBpwd())){
           bookRepository.deleteById(entity.getBno());
           return true;
       }
       return false;
    }

    //4.책 추천 목록조회
    public List<BookDto> findAll(){
       List<BookEntity> list = bookRepository.findAll();

       List<BookDto> bookDtoList = list.stream().map(entity -> entity.toBookDto())
               .collect(Collectors.toList());
       return bookDtoList;
    }

    //5. 책 개별 조회
    public BookDto findOne(int bno){
        BookEntity bookEntity = bookRepository.findById(bno).orElse(null);
        if (bookEntity == null) return null;
        BookDto bookDto = bookEntity.toBookDto();
        return bookDto;
    }



}
