package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Comment;
import tn.saturn.spring.entities.Complaint;
import tn.saturn.spring.repositories.ComplaintRepository;

public interface IComplaintService {
	public ComplaintRepository getComplaint();
	public void setComplaint(ComplaintRepository Complaint);
	public List<Complaint> retrieveAllComplaints();
	public Complaint addComplaint(Complaint ip,int idClient);
	public Complaint updateComplaint(Complaint ip);
	public Complaint retrieveComplaint(Long id);
	public List<Complaint> retrieveComplaintsByCategory(String category);
	//public void affecterCommentAComplaint(Long idComplaint,Long idComment);
	public void archiverComplaint(Long id);
	public void desarchiverComplaint(Long id);
	public void upRatingComplaint(Long id);
	public void downRatingComplaint(Long id);
	public void desarchiverAllComplaint();
	public List<Comment> retrieveAllCommentsByComplaint(Long idComplaint);
	public List<Complaint> retrieveAllReclamation();
}
