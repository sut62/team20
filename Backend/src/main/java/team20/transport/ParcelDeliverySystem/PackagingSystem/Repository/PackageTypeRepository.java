package team20.transport.ParcelDeliverySystem.PackagingStateSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;

@RepositoryRestResource
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {
     PackageType findById(lond id);
}
