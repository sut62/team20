package team20.transport.ParcelDeliverySystem.PackagingStateSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;

@RepositoryRestResource
public interface SendingTypeRepository extends JpaRepository<SendingType, Long> {
     SendingType findById(lond id);
}
