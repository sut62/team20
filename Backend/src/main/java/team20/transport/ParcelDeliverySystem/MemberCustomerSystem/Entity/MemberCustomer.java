package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    @Column(name="MEMBER_NAME",unique = false, nullable = false)
    @NotEmpty
    private String MemName;

    @Pattern(regexp = "\\d{10}")
    @Column(name="MEMBER_TEL",unique = false, nullable = false)
    @NotEmpty
    private String Tel;

    @Email
    @Column(name="MEMBER_EMAIL",unique = false, nullable = true)
    private String email;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPOLYEE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private Employee createBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberType.class)
    @JoinColumn(name = "MEMBER_TYPE_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private MemberType memberType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemberLevel.class)
    @JoinColumn(name = "MEMBER_LEVEL_ID", insertable = true)
    @JsonBackReference
    @NotNull
    private MemberLevel memberLevel;

    @OneToMany(orphanRemoval = true, mappedBy = "sentBy")
    @JsonManagedReference
    private Collection<Packaging> hasSend;
 //

}