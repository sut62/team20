package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="STATUS")
public class Status {
    @Id
    @SequenceGenerator(name="STATUS_SEQ",sequenceName="STATUS_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATUS_SEQ")
    @Column(name="STATUS_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="STATUS_NAME")
    @NotNull
    private @NonNull String name;

    @OneToMany(orphanRemoval = false, mappedBy = "onStatus")
    @JsonManagedReference
    private Collection<ShippingState> haveShippingState;


    @OneToOne(orphanRemoval = true, mappedBy = "onStatus")
    @JsonBackReference
    private Cancelsent haveCancel;

}