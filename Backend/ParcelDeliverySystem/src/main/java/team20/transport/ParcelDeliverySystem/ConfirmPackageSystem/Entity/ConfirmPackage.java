package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem
.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

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
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = IDPackage.class)
    @JoinColumn(name = "PACKAGE_ID", insertable = true)
    private IDPackage idpackage;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SatisfactionLevel.class)
    @JoinColumn(name = "SATISFACTIONLEVEL_ID", insertable = true)
    private SatisfactionLevel satisfactionLevel;

}