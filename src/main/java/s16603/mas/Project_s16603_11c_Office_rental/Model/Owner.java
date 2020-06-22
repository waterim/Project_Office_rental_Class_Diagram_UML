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
@Table(name="owner")
public class Owner extends Person{

    @NotBlank(message = "Place of birth can't be empty")
    private String placeOfBirth;

    @NotBlank(message = "Document can't be empty")
    private String document;

        public Owner(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone, @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth, @NotBlank(message = "Place of birth can't be empty") String placeOfBirth, @NotBlank(message = "Document can't be empty") String document) {
        super(name, surname, phone, dateOfBirth);
        this.placeOfBirth = placeOfBirth;
        this.document = document;
    }

    @Override
    public int calcAge() {
        return 0;
    }
}
