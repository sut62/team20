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
@Table(name="PACKAGETYPE")
public class PackageType {
    @Id
    @SequenceGenerator(name="PT_SEQ",sequenceName="PT_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PT_SEQ")
    @Column(name="PT_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;

    @OneToMany(orphanRemoval = true, mappedBy = "packageType")
    @JsonManagedReference	
    private Collection<Packaging> packaging;
}