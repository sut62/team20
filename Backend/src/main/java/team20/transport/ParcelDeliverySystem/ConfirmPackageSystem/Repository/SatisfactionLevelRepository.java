package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;

@RepositoryRestResource
public interface SatisfactionLevelRepository extends JpaRepository<SatisfactionLevel, Long> {
}
