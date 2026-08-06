package matuteferr.emifer3dcalc.modules.filament;

import matuteferr.emifer3dcalc.models.filament.Filament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilamentRepository extends MongoRepository<Filament, String> {
    boolean existsByColourAndTypeAndManufacturer(String colour, String type, String manufacturer);
}
