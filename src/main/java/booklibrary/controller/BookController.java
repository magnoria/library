package booklibrary.controller;


import booklibrary.model.dto.BookDto;
import booklibrary.model.entity.BookEntity;
import booklibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    //1. 책 등록 {"btitle" : "제목" , "bwriter" : "작가" , "bcomments" : "구름을벗어난 달처럼", "bpwd" : "123"}
    @PostMapping
    public boolean post(@RequestBody BookDto bookDto){

        boolean result = bookService.post(bookDto);

        return result;
    }

    //2. 책 개별 수정
    @PutMapping
    public boolean put(@RequestBody BookDto bookDto){
        boolean result = bookService.put(bookDto);
        return result;
    }

    //3. 책 개별 삭제
    @PostMapping("/delete")
    public boolean delete(@RequestBody BookDto bookDto){
        boolean result = bookService.delete(bookDto);
        System.out.println("bookEntity = " + bookDto);
        return  result;
    }

    //4.책 추천 목록조회
    @GetMapping
    public List<BookDto> findAll(){
        return  bookService.findAll();
    }

    //5. 책 개별 조회
    @GetMapping("/view")
    public BookDto findOne(@RequestParam int bno){
        return bookService.findOne(bno);
    }


}
