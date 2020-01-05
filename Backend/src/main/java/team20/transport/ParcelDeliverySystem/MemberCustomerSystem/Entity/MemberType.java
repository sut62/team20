package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="MEMBER_TYPE")
public class MemberType {

    @Id
    @SequenceGenerator(name="MEMBER_TYPE_SEQ",sequenceName="MEMBER_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBER_TYPE_SEQ")
    @Column(name="MEMBER_TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String type;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MemberCustomer.class)
    @JoinColumn(name = "MEMBER_CUSTOMER_ID")
    @JsonBackReference
    private Collection<MemberCustomer> MemberCustomer;


}