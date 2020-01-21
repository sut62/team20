package team20.transport.ParcelDeliverySystem.PackagingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;

import java.util.Collection;

@RepositoryRestResource
public interface PackagingRepository extends JpaRepository<Packaging, Long> {
    @Query(value = "SELECT * FROM PACKAGING WHERE PACKAGE_CODE = :package_code",nativeQuery = true)
    Packaging findPackageByCode(@Param("package_code") String package_code);
}
