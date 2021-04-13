package tn.saturn.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.saturn.spring.entities.Complaint;
import tn.saturn.spring.repositories.ComplaintRepository;
@Service
public class ComplaintServiceImpl implements IComplaintService{
	@Autowired 
	ComplaintRepository ComplaintRepository;
	
	public static final Logger L = LogManager.getLogger(ComplaintServiceImpl.class);

	public ComplaintRepository getComplaint() {
		return ComplaintRepository;
	}

	public void setComplaint(ComplaintRepository ComplaintRepository) {
		this.ComplaintRepository = ComplaintRepository;
	}
	
	@Override
	@RequestMapping("/Complaints")
	public List<Complaint> retrieveAllComplaints() {
		List<Complaint> Complaints = (List<Complaint>) ComplaintRepository.findAll();

		for (Complaint Complaint : Complaints) {
			L.info("Complaint +++" + Complaint);
			;
		}
		return Complaints;
	}

	@Override
	@RequestMapping("/addComplaint")
	public Complaint addComplaint(Complaint c) {
		return ComplaintRepository.save(c);
		
	}

	@Override
	public void deleteComplaint(String id) {
		Optional<Complaint> ComplaintOp = ComplaintRepository.findById(Long.parseLong(id));
		if (ComplaintOp.isPresent()) {
			ComplaintRepository.delete(ComplaintOp.get());
			System.out.println("Complaint deleted");
		} else {
			System.out.println("Complaint not found");
		}

	}

	@Override
	public Complaint updateComplaint(Complaint c) {
		long t = c.getIdComplaint();
		if(ComplaintRepository.findById(t).isPresent()){
			return ComplaintRepository.save(c);
		}
		else{
			System.out.println("Complaint doesn't exist !");
			return null;
		}
	}

	@Override
	public Complaint retrieveComplaint(String id) {
		Optional<Complaint> ComplaintOp = ComplaintRepository.findById(Long.parseLong(id));
		if (ComplaintOp.isPresent()) {
			ComplaintOp.get();
		} else {
			System.out.println("Complaint not found");
		}
		L.info("Complaint +++" + ComplaintOp.get());
		return ComplaintOp.get();
	}
}
