package team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name="CANCELSENT")
public class Cancelsent {
    @Id
    @SequenceGenerator(name="CANCELSENT_SEQ",sequenceName="CANCELSENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CANCELSENT_SEQ")
    @Column(name = "CANCELSENT_ID", unique = true, nullable = true)
    private Long id;

    @Column(name="SHIPPINGSTATE_CODE", nullable = false)
    @Pattern(regexp = "CN\\d{5}")
    @NotNull
    private String name;
    
    @OneToOne
    @JsonBackReference
    private Status onStatus;

    @OneToOne
    @JsonBackReference
    private Packaging onPackageing;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay.class)
    @JoinColumn(name = "HOWTOPAY_ID", insertable = true)
    @JsonBackReference
    private  Howtopay onHowtopay;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback.class)
    @JoinColumn(name = "SENTTOBACK_ID", insertable = true)
    @JsonBackReference
    private Senttoback onSenttoback;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    private Employee createBy;

}
