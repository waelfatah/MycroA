package tn.saturn.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.saturn.spring.entities.*;


import tn.saturn.spring.repositories.ClientRepository;
import tn.saturn.spring.repositories.CommentRepository;
import tn.saturn.spring.repositories.ComplaintRepository;
@Service
public class ComplaintServiceImpl implements IComplaintService{
	@Autowired 
	ComplaintRepository complaintRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ClientRepository clientRepository;
	public static final Logger L = LogManager.getLogger(ComplaintServiceImpl.class);

	public ComplaintRepository getComplaint() {
		return complaintRepository;
	}

	public void setComplaint(ComplaintRepository ComplaintRepository) {
		this.complaintRepository = ComplaintRepository;
	}
	
	@Override
	public List<Complaint> retrieveAllComplaints() {
		List<Complaint> Complaints = (List<Complaint>) complaintRepository.findAll();
		List<Complaint> complaints1=Complaints.stream().filter(c->c.getVisibility()==true).collect(Collectors.toList());
		/*for(Complaint c : complaints1){
			c.setComments(c.getComments().stream().filter(com->com.getVisibility()==true).collect(Collectors.toList()));
		}*/
		return complaints1;
	}

	@Override
	public List<Comment> retrieveAllCommentsByComplaint(Long idComplaint) {
		Complaint complaint=complaintRepository.findById(idComplaint).get();
		List<Comment> Comments = (List<Comment>) commentRepository.getAllCommentsByComplaint(complaint);
		return Comments;
	}
	
	@Override
	public List<Complaint> retrieveAllReclamation() {
		List<Complaint> Complaints = (List<Complaint>) complaintRepository.findAll();
		List<Complaint> reclamations=Complaints.stream().filter(c->c.getType()==Type.RECLAMATION).collect(Collectors.toList());	
		return reclamations;
	}
	
	@Override
	public Complaint addComplaint(Complaint c,Long idClient) {
		Client client=clientRepository.findById(idClient).get();
		c.setFkClient(client);
		return complaintRepository.save(c);
	}
	
	@Override
	public Complaint updateComplaint(Complaint c) {
		return complaintRepository.save(c);
	}

	
	@Override
	public void archiverComplaint(Long id) {
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setVisibility(false);
		complaintRepository.save(complaint);
	}

	@Override
	public void desarchiverComplaint(Long id) {
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setVisibility(true);
		complaintRepository.save(complaint);
	}
	
	@Override
	public void desarchiverAllComplaint() {
		List<Complaint> Complaints = (List<Complaint>) complaintRepository.findAll();
		for(Complaint c :Complaints){
			c.setVisibility(true);
			complaintRepository.save(c);
		}
		
	}
	
	@Override
	public Complaint retrieveComplaint(Long id) {
		/*Optional<Complaint> ComplaintOp = ComplaintRepository.findById(Long.parseLong(id));
		if (ComplaintOp.isPresent()) {
			ComplaintOp.get();
		} else {
			System.out.println("Complaint not found");
		}
		L.info("Complaint +++" + ComplaintOp.get());
		return ComplaintOp.get();*/
		 return complaintRepository.findById(id).orElse(null);
	}
	
	
	@Override
	public List<Complaint> retrieveComplaintsByCategory(String category) {
		List<Complaint> Complaints = (List<Complaint>) complaintRepository.findByCategoryAllComplaints(category);
		return Complaints;
	}
	/*
	@Override
	public void affecterCommentAComplaint(Long idComplaint,Long idComment){
		Complaint compManagedEntity=complaintRepository.findById(idComplaint).get();
		Comment commManagedEntity=commentRepository.findById(idComment).get();

		if (compManagedEntity.getComments()==null){
			List<Comment> comments =new ArrayList<>();
			comments.add(commManagedEntity);
			compManagedEntity.setComments(comments);			
		}else
			compManagedEntity.getComments().add(commManagedEntity);
		complaintRepository.save(compManagedEntity);
		commentRepository.save(commManagedEntity);
	}
	 */
	@Override
	public void upRatingComplaint(Long id){
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setRatingComplaint(complaint.getRatingComplaint()+1);
		complaintRepository.save(complaint);
	}
	@Override
	public void downRatingComplaint(Long id){
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setRatingComplaint(complaint.getRatingComplaint()-1);
		complaintRepository.save(complaint);
	}
}
