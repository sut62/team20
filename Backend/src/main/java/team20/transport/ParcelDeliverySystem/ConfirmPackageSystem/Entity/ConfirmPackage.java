package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="CONFIRM_PACKAGE")
public class ConfirmPackage {

    @Id
    @SequenceGenerator(name="confirm_package_seq",sequenceName="confirm_package_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="confirm_package_seq")  
    @Column(name = "CONFIRM_PACKAGE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="CONFIRM_PACKAGE_COMMENT", nullable = false)
    @Size(min=1, max=20)
    private String comment;

    @Column(name="CONFIRM_PACKAGE_NAME", nullable = false)
    @NotEmpty
    private String name;

    @Column(name="CONFIRM_PACKAGE_CODE", nullable = false)
    @Pattern(regexp = "CPT20\\d{5}")
    @NotNull
    private String code;

    @Column(name="Confirm_DATE" , nullable = false)
    private @NonNull Date confirmDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Employee createBy;

    @OneToOne
    @JsonBackReference
    @NotNull
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SatisfactionLevel.class)
    @JoinColumn(name = "SATISFACTIONLEVEL_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private SatisfactionLevel satisfactionLevel;

}