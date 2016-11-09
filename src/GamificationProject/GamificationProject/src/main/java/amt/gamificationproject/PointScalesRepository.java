package amt.gamificationproject;



import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pointscales", path = "pointscales")
public interface PointScalesRepository extends PagingAndSortingRepository<PointScale, Long> {

	List<PointScale> findByName(@Param("name") String name);

}
