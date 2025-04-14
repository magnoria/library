package booklibrary.model.dto;

import booklibrary.model.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor@NoArgsConstructor
public class BookDto {

    private int bno;
    private String btitle;
    private String bwriter;
    private String bcomments;
    private String bpwd;

    public BookEntity toEntity(){
        return BookEntity.builder()
                .bno(bno)
                .btitle(btitle)
                .bwriter(bwriter)
                .bcomments(bcomments)
                .bpwd(bpwd)
                .build();
    }
}
