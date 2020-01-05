package team20.transport.ParcelDeliverySystem.PackagingSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="SENDINGTYPE")
public class SendingType {
    @Id
    @SequenceGenerator(name="ST_SEQ",sequenceName="ST_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ST_SEQ")
    @Column(name="ST_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;

    @OneToMany(orphanRemoval = true, mappedBy = "sendingType")
    @JsonManagedReference
    private Collection<Packaging> send;
}