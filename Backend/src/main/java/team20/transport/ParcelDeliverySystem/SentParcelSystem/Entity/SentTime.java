package team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="SENTTIME")
public class SentTime {
    @Id
    @SequenceGenerator(name="SENTTIME_SEQ",sequenceName="SENTTIME_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTTIME_SEQ")
    @Column(name="SENTTIME_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Time fTime;
    private @NonNull Time lTime;

    @OneToMany(orphanRemoval = true, mappedBy = "senttime")
    @JsonManagedReference
    private Collection<SentParcel> sentParcel;
}