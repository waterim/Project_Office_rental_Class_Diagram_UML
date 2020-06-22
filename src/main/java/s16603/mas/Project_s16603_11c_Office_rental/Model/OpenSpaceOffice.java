package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
@Table(name = "openSpaceOffice")
public class OpenSpaceOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long openSpaceOfficeId;

    @OneToOne
    private Office office;
    @NotBlank
    private int openNumberOfSeats;

    public OpenSpaceOffice(Office office, @NotBlank int openNumberOfSeats) {
        this.office = office;
        this.openNumberOfSeats = openNumberOfSeats;
    }

    @Override
    public String toString() {
        return "OpenSpaceOffice{" +
                "openNumberOfSeats=" + openNumberOfSeats +
                '}';
    }
}
