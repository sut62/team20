package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;

import java.util.Date;
@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="CONFIRM_PACKGAGE")
public class ConfirmPackage {

    @Id
    @SequenceGenerator(name="confirm_package_seq",sequenceName="confirm_package_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="confirm_package_seq")  
    @Column(name = "CONFIRM_PACKGAGE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="Confirm_DATE")
    private @NonNull Date confirmDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    @JsonBackReference
    private Employee CreateBy;

    @OneToOne(orphanRemoval = true,mappedBy = "confirmPackage")
    @JsonBackReference
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SatisfactionLevel.class)
    @JoinColumn(name = "SATISFACTIONLEVEL_ID", insertable = true)
    @JsonBackReference
    private SatisfactionLevel satisfactionLevel;

}