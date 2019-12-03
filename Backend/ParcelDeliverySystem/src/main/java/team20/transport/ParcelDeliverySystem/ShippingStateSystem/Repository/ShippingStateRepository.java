package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;

@RepositoryRestResource
public interface ShippingStateRepository extends JpaRepository<ShippingState, Long> {

}