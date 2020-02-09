package team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name="SENTPARCEL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"SENTPARCEL_CODE"})
)
public class SentParcel {
    @Id
    @SequenceGenerator(name="SENTPARCEL_SEQ",sequenceName="SENTPARCEL_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTPARCEL_SEQ")
    @Column(name="SENTPARCEL_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Packaging.class)
    @JoinColumn(name = "PACKAGE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ORIGIN_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Station atOriginStation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ARRIVE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Station atArriveStation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SentTime.class)
    @JoinColumn(name = "SENTTIME_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private SentTime senttime;

    @Column(name="SENTPARCEL_CODE", nullable = false)
    @Pattern(regexp = "SN\\d{5}")
    @NotNull
    private String code;

}