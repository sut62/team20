package team20.transport.ParcelDeliverySystem.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="EMPOLYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="EMPOLYEE_SEQ",sequenceName="EMPOLYEE_SEQ",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPOLYEE_SEQ")
    @Column(name="EMPOLYEE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ShippingState.class)
    @JoinColumn(name = "SHIPPINGSTATE_ID")
    @JsonBackReference
    private Collection<ShippingState> haveShippingState;

}