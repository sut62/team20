package team20.transport.ParcelDeliverySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team20.transport.ParcelDeliverySystem.Entity.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}