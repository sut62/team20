package team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="SENTTIME")
public class SentTime {
    @Id
    @SequenceGenerator(name="SENTTIME_SEQ",sequenceName="SENTTIME_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTTIME_SEQ")
    @Column(name="SENTTIME_SEQ",unique = true, nullable = true)
    private @NonNull Long id;

    @OneToMany(orphanRemoval = true, mappedBy = "senttime")
    @JsonManagedReference
    private Collection<SentParcel> sentParcel;
}