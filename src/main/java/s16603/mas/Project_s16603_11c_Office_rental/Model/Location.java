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
@ToString
@Table(name="location")
public class Location {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;

    @NotBlank
    private String street;

    @NotBlank
    private String building;

    public Location(@NotBlank String street, @NotBlank String building) {
        this.street = street;
        this.building = building;
    }
}
