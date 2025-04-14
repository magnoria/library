package booklibrary.model.dto;


import booklibrary.model.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder
@NoArgsConstructor@AllArgsConstructor
public class ReviewDto {
    private int rno;
    private String rcontents; //리뷰 내용
    private String rpwd; // 리뷰 비밀번호

    public ReviewEntity reviewEntity(){
        return ReviewEntity.builder()
                .rno(rno)
                .rcontents(rcontents)
                .rpwd(rpwd)
                .build();
    }

}
