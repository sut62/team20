package team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @JsonManagedReference
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ID", insertable = true)
    @JsonManagedReference
    private Station atStation;

}