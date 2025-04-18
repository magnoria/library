package booklibrary.model.entity;

import booklibrary.model.dto.BookDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "booklibrary")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    private String btitle;
    private String bwriter;
    private String bcomments;
    private String bpwd;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;

    public BookDto toBookDto(){
        return BookDto.builder()
                .bno(bno)
                .btitle(btitle)
                .bwriter(bwriter)
                .bcomments(bcomments)
                .bpwd(bpwd)
                .build();
    }


}
