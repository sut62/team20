package team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;

public interface SentParcelRepository  extends JpaRepository<SentParcel, Long> {
}
