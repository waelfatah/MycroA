package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Comment;
import tn.saturn.spring.repositories.CommentRepository;

public interface ICommentService {
	public CommentRepository getComment();
	public void setComment(CommentRepository comment);
	//public List<Comment> retrieveAllComments(Long idComplaint);
	public Comment addComment(Comment ip,Long idComplaint);
	public void deleteComment(Long id);
	public Comment updateComment(Comment ip);
	public Comment retrieveComment(Long id);
	public void archiverComment(Long id) ;
	public void desarchiverComment(Long id); 
	public void upRatingComment(Long id);
	public void downRatingComment(Long id);
}
