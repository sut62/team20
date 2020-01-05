package team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;

import java.util.Collection;

public interface SentParcelRepository  extends JpaRepository<SentParcel, Long> {
    @Query(value = "SELECT * FROM SENTPARCEL WHERE SENTPARCEL_ID = :sentparcelId",nativeQuery = true)
    Collection<SentParcel> findBySentparcelId(@Param("sentparcelId") Long sentparcelId);
}
