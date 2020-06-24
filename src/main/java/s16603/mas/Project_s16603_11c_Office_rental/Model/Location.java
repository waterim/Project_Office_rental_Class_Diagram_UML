package s16603.mas.Project_s16603_11c_Office_rental.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name="location")
public class Location {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;

    @NotBlank
    private String street;

    @NotBlank
    private String building;

    @OneToMany
    private Set<Office> officeSet = new HashSet<>();

    public Location(@NotBlank String street, @NotBlank String building) {
        this.street = street;
        this.building = building;
    }

    public void addOffice(Office office) {
        if (office == null)
            throw new IllegalArgumentException("office must not be null");
        if (!this.officeSet.contains(office) && !office.hasLocation()) {
            officeSet.add(office);
        }
    }

    public void removeOffice(Office office) {
        if (office == null)
            throw new IllegalArgumentException("office must not be null");
        if (this.officeSet.contains(office)) {
            officeSet.remove(office);
            office.removeLocation();
        }
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
