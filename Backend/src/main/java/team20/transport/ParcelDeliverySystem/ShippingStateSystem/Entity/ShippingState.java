package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="SHIPPINGSTATE")
public class ShippingState {
    @Id
    @SequenceGenerator(name="SHIPPINGSTATE_SEQ",sequenceName="SHIPPINGSTATE_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SHIPPINGSTATE_SEQ")
    @Column(name="SHIPPINGSTATE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="SHIPPINGSTATE_TIMESTAMP", nullable = false)
    private @NonNull Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    private Employee createBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "STATUS_ID", insertable = true)
    @JsonBackReference
    private Status onStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ID", insertable = true)
    @JsonBackReference
    private Station atStation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGING_ID", insertable = true)
    @JsonBackReference
    private Packaging ofPackage;

}