package team20.transport.ParcelDeliverySystem.CancelSentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.CancelSentSystem.Entity.Senttoback;

@RepositoryRestResource
public interface SenttobackRepository extends JpaRepository<Senttobabk, Long> {
}
