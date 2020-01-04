package team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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
    private @NonNull Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "STATUS_ID", insertable = true)
    @JsonManagedReference
    private Status onStatus;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "PACKAGING_ID", insertable = true)
    @JsonManagedReference
    private Packaging onPackageing;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay.class)
    @JoinColumn(name = "HOWTOPAY_ID", insertable = true)
    @JsonManagedReference
    private team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay onHowtopay;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback.class)
    @JoinColumn(name = "SENTTOBACK_ID", insertable = true)
    @JsonManagedReference
    private team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback onSenttoback;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonManagedReference
    private Employee createBy;

}
