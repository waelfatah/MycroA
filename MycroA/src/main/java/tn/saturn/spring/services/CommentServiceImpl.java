package tn.saturn.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Comment;
import tn.saturn.spring.entities.Complaint;
import tn.saturn.spring.repositories.CommentRepository;
import tn.saturn.spring.repositories.ComplaintRepository;

@Service
public class CommentServiceImpl  implements ICommentService {
	@Autowired 
	CommentRepository commentRepository;
	
	@Autowired 
	ComplaintRepository complaintRepository;
	
	public static final Logger L = LogManager.getLogger(CommentServiceImpl.class);

	public CommentRepository getComment() {
		return commentRepository;
	}

	public void setComment(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	/*
	@Override
	public List<Comment> retrieveAllComments(Long idComment) {
		List<Comment> Comments = (List<Comment>) commentRepository.getAllCommentByComment(idComment);
		return Comments;
	}
	 */
	
	@Override
	public Comment addComment(Comment c,Long idComplaint) {
		Complaint complaint=complaintRepository.findById(idComplaint).get();
		c.setFkComplaint(complaint);
		return commentRepository.save(c);
	}
	@Override
	@RequestMapping("/updateComment")
	public Comment updateComment(Comment c) {
		return commentRepository.save(c);
	}

	@Override
	public void deleteComment(Long id) {
		Optional<Comment> CommentOp = commentRepository.findById(id);
		if (CommentOp.isPresent()) {
			commentRepository.delete(CommentOp.get());
			System.out.println("Comment deleted");
		} else {
			System.out.println("Comment not found");
		}
	}

	@Override
	public Comment retrieveComment(Long id) {
		/*Optional<Comment> CommentOp = CommentRepository.findById(Long.parseLong(id));
		if (CommentOp.isPresent()) {
			CommentOp.get();
		} else {
			System.out.println("Comment not found");
		}
		L.info("Comment +++" + CommentOp.get());
		return CommentOp.get();*/
		
		 return commentRepository.findById(id).orElse(null);
	}
	
	@Override
	public void archiverComment(Long id) {
		Comment comment = commentRepository.findById(id).get();
		comment.setVisibility(false);
		commentRepository.save(comment);
	}

	@Override
	public void desarchiverComment(Long id) {
		Comment comment = commentRepository.findById(id).get();
		comment.setVisibility(true);
		commentRepository.save(comment);
	}
	
	@Override
	public void upRatingComment(Long id){
		Comment comment = commentRepository.findById(id).get();
		comment.setRatingComment(comment.getRatingComment()+1);
		commentRepository.save(comment);
	}
	@Override
	public void downRatingComment(Long id){
		Comment comment = commentRepository.findById(id).get();
		comment.setRatingComment(comment.getRatingComment()-1);
		commentRepository.save(comment);
	}
}
