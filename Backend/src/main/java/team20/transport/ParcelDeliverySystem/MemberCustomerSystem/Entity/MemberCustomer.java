package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

@Entity
@Data
@NoArgsConstructor
@Table(name="MEMBER_CUSTOMER")
public class MemberCustomer {

    @Id
    @SequenceGenerator(name="MEMBER_CUSTOMER_SEQ",sequenceName="MEMBER_CUSTOMER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBER_CUSTOMER_SEQ")
    @Column(name="MEMBER_CUSTOMER_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private String MemName;
    private long Tel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    private Employee createBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberType.class)
    @JoinColumn(name = "MEMBER_TYPE_ID", insertable = true)
    @JsonBackReference
    private MemberType memberType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberLevel.class)
    @JoinColumn(name = "MEMBER_LEVEL_ID", insertable = true)
    @JsonBackReference
    private MemberLevel memberLevel;

    @OneToMany(orphanRemoval = true, mappedBy = "sentBy")
    @JsonManagedReference
    private Collection<Packaging> hasSend;
 //

}