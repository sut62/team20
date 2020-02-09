package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(
        name="SHIPPINGSTATE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"SHIPPINGSTATE_CODE"})
)
public class ShippingState {
    @Id
    @SequenceGenerator(name = "SHIPPINGSTATE_SEQ", sequenceName = "SHIPPINGSTATE_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIPPINGSTATE_SEQ")
    @Column(name = "SHIPPINGSTATE_ID", unique = true, nullable = true)
    private Long id;

    @Column(name="SHIPPINGSTATE_CODE", nullable = false)
    @Pattern(regexp = "SHPT20\\d{5}_\\d{2}")
    @NotNull
    private String code;


    @Column(name="SHIPPINGSTATE_TIMESTAMP", nullable = false)
    @NotNull
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Employee createBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "STATUS_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Status onStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Station atStation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGING_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Packaging ofPackage;

}