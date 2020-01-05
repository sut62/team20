package team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="SENTPARCEL")
public class SentParcel {
    @Id
    @SequenceGenerator(name="SENTPARCEL_SEQ",sequenceName="SENTPARCEL_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTPARCEL_SEQ")
    @Column(name="SENTPARCEL_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGING_ID", insertable = true)
    @JsonBackReference
    private Collection<Packaging> packaging;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ID", insertable = true)
    @JsonBackReference
    private Station atStation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SentTime.class)
    @JoinColumn(name = "SENTTIME_ID", insertable = true)
    @JsonBackReference
    private SentTime senttime;
}