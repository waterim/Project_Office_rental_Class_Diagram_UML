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
@Table(name = "virtualOffice")
public class VirtualOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long virtualOfficeId;

    @OneToOne
    private Office office;
    @NotBlank
    private int virtualVideoConversationSize;

    public VirtualOffice(Office office, @NotBlank int virtualVideoConversationSize) {
        this.office = office;
        this.virtualVideoConversationSize = virtualVideoConversationSize;
    }

    @Override
    public String toString() {
        return "VirtualOffice{" +
                "virtualVideoConversationSize=" + virtualVideoConversationSize +
                '}';
    }
}
