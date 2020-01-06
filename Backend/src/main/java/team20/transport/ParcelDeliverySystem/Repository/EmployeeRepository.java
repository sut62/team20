package team20.transport.ParcelDeliverySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.Entity.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM EMPOLYEE WHERE EMPOLYEE_ID = :employeeId AND EMPOLYEE_EMAIL = :email",nativeQuery = true)
    Employee findByIdAndEmail(@Param("employeeId") Long employeeId, @Param("email") String email);
}