package tn.saturn.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Complaint;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.entities.InsuredProperty;
import tn.saturn.spring.entities.PropertyType;
import tn.saturn.spring.entities.Role;
import tn.saturn.spring.services.IEmployeeService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MycroAApplication.class)
public class MycroAApplicationTests {
	@Autowired
	IEmployeeService es;
	

	

	@Test
	public void contextLoads() throws ParseException{
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date d1 = dateFormat.parse("2021-03-31");
		 Date d2 = dateFormat.parse("2022-03-30");
		 //long idP = ps.retrieveInsuredProperty("1").getIdProperty();
		 tn.saturn.spring.entities.Client cl= null;
		 //sinon :
		// LocalDateTime date2 = currentTime.withDayOfMonth(25).withYear(2023).withMonth(12);
		// System.out.println("Date modifiée : " + date2);
		 
		 //InsuredProperty ip = new InsuredProperty(10000.0f, PropertyType.VEHICULE, true);
		// Contract contract = new Contract(d1,d2,"Toute option",32.5f,1,true,1,1);
		 Employee employee = new Employee("employee1","Employee1",22414850,12036989, "feryel.dhaouadi@esprit.tn", 1333.2f, "em1", "em1", "carthage", 2,Role.EXPERT,true);
				
		 System.out.println(employee.getIdEmployee());
		// Complaint complaint=new Complaint("category","description",true);
		 es.addEmployee(employee);
		 //cs.addContract(contract);
		//ps.addInsuredProperty(ip);
		 for(Employee EmployeeE:es.retrieveAllEmployees()){
			 System.out.println(EmployeeE.toString());
		 }
	}
	

}