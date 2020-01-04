package team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentTime;

public interface SentTimeRepository extends JpaRepository<SentTime, Long> {
}
