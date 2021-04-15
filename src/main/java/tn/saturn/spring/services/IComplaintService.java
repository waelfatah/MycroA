package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Complaint;
import tn.saturn.spring.repositories.ComplaintRepository;

public interface IComplaintService {
	public ComplaintRepository getComplaint();
	public void setComplaint(ComplaintRepository Complaint);
	public List<Complaint> retrieveAllComplaints();
	public Complaint addComplaint(Complaint ip);
	public void deleteComplaint(String id);
	public Complaint updateComplaint(Complaint ip);
	public Complaint retrieveComplaint(String id);

}
