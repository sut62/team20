package team20.transport.ParcelDeliverySystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="EMPOLYEEPOSITION")
public class EmployeePosition {
    @Id
    @SequenceGenerator(name = "EMPOLYEEPOSITION_SEQ", sequenceName = "EMPOLYEEPOSITION_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPOLYEEPOSITION_SEQ")
    @Column(name = "EMPOLYEEPOSITION_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "EMPOLYEEPOSITION_NPOSITION", unique = true, nullable = true)
    private @NonNull String position;

    @OneToMany(orphanRemoval = true, mappedBy = "employeePosition")
    @JsonManagedReference
    private Collection<Employee> haveEmployee;
}