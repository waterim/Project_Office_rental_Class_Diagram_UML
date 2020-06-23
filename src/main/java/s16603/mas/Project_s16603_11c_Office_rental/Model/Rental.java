package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentalId;

    @NotBlank
    private String name;
    @NotNull
    private LocalDate start_date;
    @NotNull
    private LocalDate end_date;

    @ManyToOne
    private Office office;
    @ManyToOne
    private Customer customer;

    @OneToMany
    private Set<Comment> comments = new HashSet<>();

    public Rental(@NotBlank String name, @NotNull LocalDate start_date, @NotNull LocalDate end_date, Customer customer, Office office) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.office = office;
        this.customer = customer;
    }


    public void removeRental() {
        if(this.customer != null && this.office != null) {
            if(this.customer != null) {
                this.customer.removeRental(this);
                this.customer = null;
            }
            if(this.office != null) {
                this.office.removeRental(this);
                this.office = null;
            }
        }
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.addToRental(this);
    }

    public void removeComment(Comment comment){
        if(comments.contains(comment)) {
            comments.remove(comment);
            comment.removeFromRental();
        }
        else{
            System.out.println("No such comment");
        }
    }



}
