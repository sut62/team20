package team20.transport.ParcelDeliverySystem.PackagingSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="PACKAGING")
public class Packaging {
    @Id
    @SequenceGenerator(name="PACKAGING_SEQ",sequenceName="PACKAGING_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PACKAGING_SEQ")
    @Column(name="PACKAGING_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String reciever;
    private @NonNull String place;
    private @NonNull Long weight;
    private @NonNull Long volume;
    private @NonNull Date packageDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberCustomer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    @JsonManagedReference
    private MemberCustomer sentBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonManagedReference
    private Employee createBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ID", insertable = true)
    @JsonManagedReference
    private Station atStation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PackageType.class)
    @JoinColumn(name = "PTYPE_ID", insertable = true)
    private PackageType packageType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SendingType.class)
    @JoinColumn(name = "STYPE_ID", insertable = true)
    private SendingType sendingType;
  
    @OneToMany(fetch = FetchType.LAZY, targetEntity = ShippingState.class)
    @JoinColumn(name = "SHIPPINGSTATE_ID")
    @JsonBackReference
    private Collection<ShippingState> haveShippingState;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SentParcel.class)
    @JoinColumn(name = "SENTPARCEL_ID", insertable = true)
    @JsonManagedReference
    private SentParcel sentParcel;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Cancelsent.class)
    @JoinColumn(name = "CANCELSENT_ID", insertable = true)
    @JsonManagedReference
    private Cancelsent cancelsent;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = ConfirmPackage.class)
    @JoinColumn(name = "CONFIRMPACKAGE_ID", insertable = true)
    @JsonManagedReference
    private ConfirmPackage confirmPackage;
}