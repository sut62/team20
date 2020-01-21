package team20.transport.ParcelDeliverySystem;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.HowtopayRepository;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.SenttobackRepository;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.EmployeePosition;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberLevelRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackageTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;
import team20.transport.ParcelDeliverySystem.Repository.EmployeePositionRepository;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.SatisfactionLevelRepository;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class ParcelDeliverySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelDeliverySystemApplication.class, args);
	}

	@Bean
	ApplicationRunner init(
			MemberLevelRepository memberLevelRepository,
			MemberTypeRepository memberTypeRepository,
			SendingTypeRepository sendingTypeRepository,
			PackageTypeRepository packageTypeRepository,
			EmployeeRepository employeeRepository,
			EmployeePositionRepository employeePositionRepository,
			StatusRepository statusRepository,
			StationRepository stationRepository,
			HowtopayRepository howtopayRepository,
			SenttobackRepository senttobackRepository,
			SatisfactionLevelRepository satisfactionLevelRepository
	){
		return args -> {
			String [] allStation = { "มทส","เดอะมอลโคราช","บุรีรัมย์","สีคิ้ว" };
			String []  allStatus = { "พัสดุลงทะเบียน","สำเร็จ","ยกเลิก","ระหว่างจัดส่ง" };
			String [] allEmployeePosition = { "พนักงานจัดการพัสดุ","พนักงานจัดส่งพัสดุ","พนักงานรับฝากพัสดุ" };
			Object [][] allEmployee = {
					{"chatdanai374@gmail.com","Chatdanai Phakaket",1L},
					{"pattarasit0@gmail.com","Pattarasit Lomthaisong",2L},
					{"konggmhw@gmail.com","Pairat Tonkean",3L},
					{"Riw.Kittitorn@gmail.com","Kittitorn Seangjeen",2L},
					{"pream.thamai@gmail.com","Chaiwin Siriphan",3L},
					{"athithan23123@gmail.com","Athitan Jitsopaporn",1L}
			};
			String [] allMemberLevel = {"Regular","Premium"};
			String [] allMemberType = { "Individual","Company","Business" };
			String [] allHowToPay = {"เงินสด","บัตรเครดิต"};
			String [] allSentToBack = {"รับที่ต้นทาง","รับที่ปัจจุบัน"};
			Object [][] allSendingType = {
					{"EMS",20},
					{"Register",10}
			};
			String [] allPackageType = {"ขนาดเล็ก","ขนาดกลาง","ขนาดใหญ่","เอกสาร"};
			String [] allSatisfactionLevel = {"ไม่พอใจ","พอใจ","พอใจมาก"};

			Collection<Station> initStation = new ArrayList<Station>();
			for(String x: allStation){
				Station newStation = new Station();
				newStation.setName(x);
				initStation.add(newStation);
			}
			stationRepository.saveAll(initStation);

			Collection<Status> initStatus = new ArrayList<Status>();
			for(String x: allStatus){
				Status newStatus = new Status();
				newStatus.setName(x);
				initStatus.add(newStatus);
			}
			statusRepository.saveAll(initStatus);

			Collection<EmployeePosition> initEmployeePosition = new ArrayList<EmployeePosition>();
			for(String x: allEmployeePosition){
				EmployeePosition newEmployeePosition = new EmployeePosition();
				newEmployeePosition.setPosition(x);
				initEmployeePosition.add(newEmployeePosition);
			}
			employeePositionRepository.saveAll(initEmployeePosition);

			Collection<Employee> initEmployee = new ArrayList<Employee>();
			for(Object[] x: allEmployee){
				Employee newEmployee = new Employee();
				newEmployee.setEmail(x[0].toString());
				newEmployee.setName(x[1].toString());
				newEmployee.setEmployeePosition(employeePositionRepository.findById(Long.valueOf(x[2].toString())).get());
				initEmployee.add(newEmployee);
			}
			employeeRepository.saveAll(initEmployee);

			Collection<MemberLevel> initMemberLevel = new ArrayList<MemberLevel>();
			for(String x: allMemberLevel){
				MemberLevel newMemberLevel = new MemberLevel();
				newMemberLevel.setPermission(x);
				initMemberLevel.add(newMemberLevel);
			}
			memberLevelRepository.saveAll(initMemberLevel);

			Collection<MemberType> initMemberType = new ArrayList<MemberType>();
			for(String x: allMemberType){
				MemberType newMemberType = new MemberType();
				newMemberType.setType(x);
				initMemberType.add(newMemberType);
			}
			memberTypeRepository.saveAll(initMemberType);

			Collection<Howtopay> initHowtopay = new ArrayList<Howtopay>();
			for(String x: allHowToPay){
				Howtopay newHowtopay = new Howtopay();
				newHowtopay.setName(x);
				initHowtopay.add(newHowtopay);
			}
			howtopayRepository.saveAll(initHowtopay);

			Collection<Senttoback> initSenttoback = new ArrayList<Senttoback>();
			for(String x: allSentToBack){
				Senttoback newSenttoback = new Senttoback();
				newSenttoback.setName(x);
				initSenttoback.add(newSenttoback);
			}
			senttobackRepository.saveAll(initSenttoback);

			Collection<SendingType> initSendingType = new ArrayList<SendingType>();
			for(Object[] x: allSendingType){
				SendingType newSendingType = new SendingType();
				newSendingType.setType(x[0].toString());
				newSendingType.setUnit(Integer.parseInt(x[1].toString()));
				initSendingType.add(newSendingType);
			}
			sendingTypeRepository.saveAll(initSendingType);

			Collection<PackageType> initPackageType = new ArrayList<PackageType>();
			for(String x: allPackageType){
				PackageType newPackageType = new PackageType();
				newPackageType.setType(x);
				initPackageType.add(newPackageType);
			}
			packageTypeRepository.saveAll(initPackageType);

			Collection<SatisfactionLevel> initSatisfactionLevel = new ArrayList<SatisfactionLevel>();
			for(String x: allSatisfactionLevel){
				SatisfactionLevel newSatisfactionLevel = new SatisfactionLevel();
				newSatisfactionLevel.setSatisfactionlevel_name(x);
				initSatisfactionLevel.add(newSatisfactionLevel);
			}
			satisfactionLevelRepository.saveAll(initSatisfactionLevel);

		};
	}
}
