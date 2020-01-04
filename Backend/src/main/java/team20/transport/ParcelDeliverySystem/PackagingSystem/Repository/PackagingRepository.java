package team20.transport.ParcelDeliverySystem.PackagingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

@RepositoryRestResource
public interface PackagingRepository extends JpaRepository<Packaging, Long> {
}
