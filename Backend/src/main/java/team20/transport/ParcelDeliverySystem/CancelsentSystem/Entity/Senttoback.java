package team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="SENTTOBACK")
public class Senttoback {
    @Id
    @SequenceGenerator(name="SENTTOBACK_SEQ",sequenceName="SENTTOBACK_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENTTOBACK_SEQ")
    @Column(name="SENTTOBACK_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="SENTTOBACK_NAME")
    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Cancelsent.class)
    @JoinColumn(name = "CANCELSENT_ID")
    @JsonManagedReference
    private Collection<Cancelsent> haveCancelSenttoBack;
}