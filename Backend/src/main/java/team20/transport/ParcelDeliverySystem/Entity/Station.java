package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="STATION")
public class Station {
    @Id
    @SequenceGenerator(name="STATION_SEQ",sequenceName="STATION_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATION_SEQ")
    @Column(name="STATION_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ShippingState.class)
    @JoinColumn(name = "SHIPPINGSTATE_ID")
    @JsonBackReference
    private Collection<ShippingState> haveShippingState;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = SentParcel.class)
    @JoinColumn(name = "SENTPARCEL_ID")
    @JsonBackReference
    private Collection<SentParcel> sentparcel;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGING_ID")
    @JsonBackReference
    private Collection<Packaging> package;
}