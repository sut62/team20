package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="MEMBER_LEVEL")
public class MemberLevel {

    @Id
    @SequenceGenerator(name="MEMBER_LEVEL_SEQ",sequenceName="MEMBER_LEVEL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBER_LEVEL_SEQ")
    @Column(name="MEMBER_LEVEL_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String permission;

    @OneToMany(orphanRemoval = true, mappedBy = "memberLevel")
    @JsonManagedReference
    private Collection<MemberCustomer> memberCustomer;


}