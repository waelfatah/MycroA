package tn.saturn.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.saturn.spring.entities.Comment;
import tn.saturn.spring.services.ICommentService;

@RestController
public class CommentController {
	@Autowired
	ICommentService commentService;
	/*
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/get-AllComments/{id}")
	@ResponseBody
	public List<Comment> getComments(@PathVariable ("id") Long id) {
	return commentService.retrieveAllComments(id);
	}*/
	/*
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/retrieveComment/{id}")
	@ResponseBody
	public Comment retrieveUser(@PathVariable ("id") Long id) {
	return commentService.retrieveComment(id);
	}
	*/
	@PostMapping("/addComment/{id}")
	@ResponseBody
	public Comment addComment(@RequestBody Comment c,@PathVariable("id") Long compId) {
		Comment Comment = commentService.addComment(c,compId);
	return Comment;
	}
	
	@PostMapping("/updateComment")
	@ResponseBody
	public Comment updateComment(@RequestBody Comment c) {
		Comment Comment = commentService.updateComment(c);
	return Comment;
	}
	
	@PutMapping("/archiverComment/{id}")
	@ResponseBody
	public void archiverComplaint(@PathVariable("id") Long commentId) {
	commentService.archiverComment(commentId);
	}
	
	@PutMapping("/desarchiverComment/{id}")
	@ResponseBody
	public void desarchiverComment(@PathVariable("id") Long commentId) {
	commentService.desarchiverComment(commentId);
	}
	
	@PutMapping("/upRatingComment/{idcomm}")
	@ResponseBody
	public void upRatingComment(@PathVariable("idcomm") Long idcomm){
		commentService.upRatingComment(idcomm);
	}
	
	@PutMapping("/downRatingComment/{idcomm}")
	@ResponseBody
	public void downRatingComment(@PathVariable("idcomm") Long idcomm){
		commentService.downRatingComment(idcomm);
	}

}
