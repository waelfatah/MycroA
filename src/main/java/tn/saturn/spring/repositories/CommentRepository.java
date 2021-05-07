package tn.saturn.spring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Comment;
import tn.saturn.spring.entities.Complaint;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long >{

	/*@Query("Select  "
			+ "Distinct com From Comment com "
			+ "join com.complaint a "
			+ "where a.idComplaint=:compId ")
	public List<Comment> getAllCommentByComplaint(@Param("compId")Long idComplaint);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Comment (description, visibility, author, ratingComment) VALUES (:cat, :desc, :vis, :auth, :rat)", nativeQuery = true)
	void insertComment(@Param("cat") String cat, @Param("desc") String  desc, @Param("vis") Boolean vis, @Param("auth") String auth, @Param("rat") Integer rat);
	 */
	@Query("Select com From Comment com where com.fkComplaint=:complaint")
	public List<Comment> getAllCommentsByComplaint(@Param("complaint")Complaint complaint);
}
