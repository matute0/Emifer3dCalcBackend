package matuteferr.emifer3dcalc.modules.cost;

import matuteferr.emifer3dcalc.models.cost.Cost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends MongoRepository<Cost, String> {
}
