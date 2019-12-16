package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
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

    private @NonNull int permission;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<MemberCustomer> MemberCustomer;


}