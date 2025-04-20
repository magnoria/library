package booklibrary.model.dto;


import booklibrary.model.entity.BookEntity;
import booklibrary.model.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder
@NoArgsConstructor@AllArgsConstructor
public class ReviewDto {
    private int rno;
    private String rname;
    private String rcontents; //리뷰 내용
    private String rpwd; // 리뷰 비밀번호

    private int bno;


    public ReviewEntity reviewEntity(){
        return ReviewEntity.builder()
                .rno(rno)
                .rname(rname)
                .rcontents(rcontents)
                .rpwd(rpwd)
                .build();
    }

}
