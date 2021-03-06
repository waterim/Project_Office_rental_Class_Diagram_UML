package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String text;

    @ManyToOne
    private Rental rental;

    public Comment(@NotBlank String name, @NotNull LocalDate date, @NotBlank String text) {
        this.name = name;
        this.date = date;
        this.text = text;
    }

    public void addToRental(Rental rental){
        rental.addComment(this);
        setRental(rental);
    }
    public void removeFromRental(){
        if(this.getRental() != null){
            setRental(null);
            this.rental.removeComment(this);
        }
        else {
            System.out.println("No such rental");
        }
    }
}
