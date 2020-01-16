package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name="STATION_NAME")
    @NotNull
    private @NonNull String name;

    @OneToMany(orphanRemoval = false, mappedBy = "atStation")
    @JsonManagedReference
    private Collection<ShippingState> haveShippingState;

    @OneToMany(orphanRemoval = true, mappedBy = "atOriginStation")
    @JsonBackReference
    private Collection<SentParcel> sentparcel;

    @OneToMany(orphanRemoval = true, mappedBy = "atArriveStation")
    @JsonBackReference
    private Collection<SentParcel> receiveparcel;

    @OneToMany(orphanRemoval = true, mappedBy = "atStation")
    @JsonManagedReference
    private Collection<Packaging> packaging;
}