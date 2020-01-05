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
    private @NonNull Long id;

    @Column(name="EMPOLYEE_NAME", nullable = true)
    private @NonNull String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EmployeePosition.class)
    @JoinColumn(name = "EMPOLYEEPOSITION_ID")
    @JsonManagedReference
    private EmployeePosition employeePosition;

    @OneToMany(orphanRemoval = true, mappedBy = "createBy")
    @JsonManagedReference
    private Collection<ShippingState> haveShippingState;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MemberCustomer.class)
    @JoinColumn(name = "MEMBER_CUSTOMER_ID")
    @JsonBackReference
    private Collection<MemberCustomer> RegisterMember;

    @OneToMany(orphanRemoval = true,mappedBy = "CreateBy")
    @JsonBackReference
    private Collection<ConfirmPackage> confirm;

    @OneToMany(orphanRemoval = true, mappedBy = "createBy")
    @JsonManagedReference
    private Collection<Packaging> send;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Cancelsent.class)
    @JoinColumn(name = "CANCELSENT_ID")
    @JsonBackReference
    private Collection<Cancelsent> haveCancel;

}