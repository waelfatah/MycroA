package tn.saturn.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.saturn.spring.entities.Comment;
import tn.saturn.spring.entities.Complaint;
import tn.saturn.spring.services.IComplaintService;

@RestController
public class ComplaintController {
	@Autowired
	IComplaintService complaintService;
	
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/get-AllComplaints")
	@ResponseBody
	public List<Complaint> getComplaints() {
	return complaintService.retrieveAllComplaints();
	}

	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/get-AllReclamations")
	@ResponseBody
	public List<Complaint> getReclamations() {
	return complaintService.retrieveAllReclamation();
	}
		
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/get-AllCommentsByComplaint/{id}")
	@ResponseBody
	public List<Comment> getCommentsByComplaint(@PathVariable ("id") Long id) {
	return complaintService.retrieveAllCommentsByComplaint(id);
	}
	
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/retrieveComplaint/{id}")
	@ResponseBody
	public Complaint retrieveComplaint(@PathVariable ("id") Long id) {
	return complaintService.retrieveComplaint(id);
	}
	
	@PostMapping("/addComplaint/{id}")
	@ResponseBody
	public Complaint addComplaint(@RequestBody Complaint c,@PathVariable ("id") int id) {
		Complaint complaint = complaintService.addComplaint(c,id);
	return complaint;
	}
	
	@PostMapping("/updateComplaint")
	@ResponseBody
	public Complaint updateComplaint(@RequestBody Complaint c) {
		Complaint complaint = complaintService.updateComplaint(c);
	return complaint;
	}
	
	@PutMapping("/archiverComplaint/{id}")
	@ResponseBody
	public void archiverComplaint(@PathVariable("id") Long complaintId) {
	complaintService.archiverComplaint(complaintId);
	}
	
	@PutMapping("/desarchiverComplaint/{id}")
	@ResponseBody
	public void desarchiverComplaint(@PathVariable("id") Long complaintId) {
	complaintService.desarchiverComplaint(complaintId);
	}
	
	@PutMapping("/desarchiverAllComplaint")
	@ResponseBody
	public void desarchiverAllComplaint() {
	complaintService.desarchiverAllComplaint();
	}

	@GetMapping("/retrieveComplaintsByCategory/{category}")
	@ResponseBody
	public List<Complaint>  getComplaintsByCategory(@PathVariable("category") String category) {
	return complaintService.retrieveComplaintsByCategory(category);
	}
	/*
	@PutMapping("/AffecterCommentAComplaint/{idcomp}/{idcomm}")
	@ResponseBody
	public void AffectCommAComp(@PathVariable("idcomp") Long idcomp,@PathVariable("idcomm") Long idcomm){
		complaintService.affecterCommentAComplaint(idcomp,idcomm);
	}
	*/
	@PutMapping("/upRatingComplaint/{idcomp}")
	@ResponseBody
	public void upRatingComplaint(@PathVariable("idcomp") Long idcomp){
		complaintService.upRatingComplaint(idcomp);
	}
	
	@PutMapping("/downRatingComplaint/{idcomp}")
	@ResponseBody
	public void downRatingComplaint(@PathVariable("idcomp") Long idcomp){
		complaintService.downRatingComplaint(idcomp);
	}
}
