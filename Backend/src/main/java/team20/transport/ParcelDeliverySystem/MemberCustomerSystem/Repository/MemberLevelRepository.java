package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;

@RepositoryRestResource
public interface MemberLevelRepository extends JpaRepository<MemberLevel, Long> {
}
