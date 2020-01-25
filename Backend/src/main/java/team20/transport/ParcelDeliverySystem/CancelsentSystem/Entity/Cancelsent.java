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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @Column(name="CANCLESENT_NAME", nullable = false)
    @Pattern(regexp = "CN\\d{5}")
    @NotNull
    private String name;

    @Column(name="CANCLESENT_COMMENT", nullable = false)
    @NotEmpty
    private String comment;
    
    @OneToOne
    @JsonBackReference
    @NotNull
    private Status onStatus;

    @OneToOne
    @JsonBackReference
    @NotNull
    private Packaging onPackageing;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay.class)
    @JoinColumn(name = "HOWTOPAY_ID", insertable = true)
    @JsonBackReference
    @NotNull
    @Size(min=1, max=20)
    private  Howtopay onHowtopay;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback.class)
    @JoinColumn(name = "SENTTOBACK_ID", insertable = true)
    @JsonBackReference
    @NotNull
    @Size(min=1, max=20)
    private Senttoback onSenttoback;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Employee createBy;

}
