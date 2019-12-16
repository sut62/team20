package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;

@RepositoryRestResource
public interface MemberTypeRepository extends JpaRepository<MemberType, Long> {
}
