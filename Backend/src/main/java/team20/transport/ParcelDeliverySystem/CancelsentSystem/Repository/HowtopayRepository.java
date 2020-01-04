package team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay;

@RepositoryRestResource
public interface HowtopayRepository extends JpaRepository<Howtopay, Long> {
}

