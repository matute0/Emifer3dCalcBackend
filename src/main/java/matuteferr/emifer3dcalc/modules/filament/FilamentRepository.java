package matuteferr.emifer3dcalc.modules.filament;

import jakarta.validation.constraints.NotBlank;
import matuteferr.emifer3dcalc.models.filament.Filament;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilamentRepository extends MongoRepository<Filament, String> {
    boolean existsByColourAndTypeAndManufacturer(String colour, String type, String manufacturer);

    List<GETFilamentDTO> findAllByStatus(boolean status);

    Optional<Filament> findByColourAndTypeAndManufacturer(@NotBlank String colour, @NotBlank String type, @NotBlank String manufacturer);
}
