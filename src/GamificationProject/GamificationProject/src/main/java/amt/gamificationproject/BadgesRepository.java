package amt.gamificationproject;



import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "badges", path = "badges")
public interface BadgesRepository extends PagingAndSortingRepository<Badge, Long> {

	List<Badge> findByName(@Param("name") String name);

}