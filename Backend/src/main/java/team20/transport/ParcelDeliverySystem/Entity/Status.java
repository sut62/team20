package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ShippingState.class)
    @JoinColumn(name = "SHIPPINGSTATE_ID")
    @JsonBackReference
    private Collection<ShippingState> haveShippingState;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGING_ID")
    @JsonBackReference
    private Collection<Packaging> havePackaging;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Cancelsent.class)
    @JoinColumn(name = "CANCELSENT_ID")
    @JsonBackReference
    private Collection<Cancelsent> haveCansel;
}