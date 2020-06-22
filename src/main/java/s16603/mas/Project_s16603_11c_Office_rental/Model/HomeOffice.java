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
@Table(name = "homeOffice")
public class HomeOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long homeOfficeId;

    @OneToOne
    private Office office;
    @NotBlank
    private int homeWorkingDaysPerWeek;

    public HomeOffice(Office office, @NotBlank int homeWorkingDaysPerWeek) {
        this.office = office;
        this.homeWorkingDaysPerWeek = homeWorkingDaysPerWeek;
    }

    @Override
    public String toString() {
        return "HomeOffice{" +
                "homeWorkingDaysPerWeek=" + homeWorkingDaysPerWeek +
                '}';
    }
}
