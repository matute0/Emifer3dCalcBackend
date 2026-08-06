package matuteferr.emifer3dcalc.models.filament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "filaments")
@Data
public class Filament {
    @Id
    private String id;
    private String colour;
    private String type;
    private String manufacturer;
    private boolean status;
}
