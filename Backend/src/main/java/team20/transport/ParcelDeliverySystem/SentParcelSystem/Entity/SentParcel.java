package team20.transport.ParcelDeliverySystem.SnetParcelSystem.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="SENTPARCEL")
public class Sentparcel {
    @Id
    @SequenceGenerator(name="SENTPARCEL_SEQ",sequenceName="SENTPARCEL_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTPARCEL_SEQ")
    @Column(name="SENTPARCEL_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonManagedReference
    private Employee createBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "STATUS_ID", insertable = true)
    @JsonManagedReference
    private Status onStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Station.class)
    @JoinColumn(name = "STATION_ID", insertable = true)
    @JsonManagedReference
    private Station atStation;
//

}