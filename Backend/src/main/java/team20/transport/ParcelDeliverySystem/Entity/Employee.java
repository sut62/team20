package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="EMPOLYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="EMPOLYEE_SEQ",sequenceName="EMPOLYEE_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPOLYEE_SEQ")
    @Column(name="EMPOLYEE_ID",unique = true, nullable = true)
    private @NotNull Long id;

    @Column(name="EMPOLYEE_NAME")
    private @NotNull String name;

    @Column(name="EMPOLYEE_EMAIL")
    @Email
    private @NotNull String email;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EmployeePosition.class)
    @JoinColumn(name = "EMPOLYEEPOSITION_ID")
    @JsonBackReference
    private EmployeePosition employeePosition;

    @OneToMany(orphanRemoval = false, mappedBy = "createBy")
    @JsonManagedReference
    private Collection<ShippingState> haveShippingState;

    @OneToMany(orphanRemoval = true, mappedBy = "createBy")
    @JsonManagedReference
    private Collection<MemberCustomer> registerMember;

    @OneToMany(orphanRemoval = true,mappedBy = "createBy")
    @JsonManagedReference
    private Collection<ConfirmPackage> confirmPackage;

    @OneToMany(orphanRemoval = true, mappedBy = "createBy")
    @JsonManagedReference
    private Collection<Packaging> send;

    @OneToMany(orphanRemoval = true, mappedBy = "createBy")
    @JsonManagedReference
    private Collection<Cancelsent> haveCancel;

}