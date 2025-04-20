package booklibrary.model.entity;

import booklibrary.model.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReviewEntity {
    // 리뷰 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rname;
    private String rcontents; //리뷰 내용
    private String rpwd; // 리뷰 비밀번호




    @ManyToOne
    @JoinColumn(name = "bno") // 외래 키 컬럼명 지정
    private BookEntity book;

    public ReviewDto reviewDto(){
        return ReviewDto.builder()
                .rno(rno)
                .rname(rname)
                .rcontents(rcontents)
                .rpwd(rpwd)
                .bno(this.book.getBno())
                .build();
    }

}
