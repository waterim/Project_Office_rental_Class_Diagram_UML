package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="person")

public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Surname can't be blank")
    private String surname;
    @NotBlank(message = "Phone can't be blank")
    private String phone;
    @NotNull(message = "Date of birth can't be blank")
    private LocalDate dateOfBirth;

    public abstract int calcAge();

    public Person(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone, @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

}
