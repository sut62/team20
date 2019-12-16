package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;

@RepositoryRestResource
public interface ConfirmPackageRepository extends JpaRepository<ConfirmPackage, Long> {
}
