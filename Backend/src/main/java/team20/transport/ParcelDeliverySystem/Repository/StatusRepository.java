package team20.transport.ParcelDeliverySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.Entity.Status;

@RepositoryRestResource
public interface StatusRepository extends JpaRepository<Status, Long> {
}