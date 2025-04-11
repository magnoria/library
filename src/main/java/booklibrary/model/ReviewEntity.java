package booklibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "review")
@Data
@RequiredArgsConstructor
public class ReviewEntity {
    // 리뷰 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rcontents; //리뷰 내용
    private String rpwd; // 리뷰 비밀번호



    @ManyToOne
    @JoinColumn(name = "bno") // 외래 키 컬럼명 지정
    private BookEntity book;

}
