package matuteferr.emifer3dcalc.config;

import matuteferr.emifer3dcalc.models.cost.CostConfig;
import matuteferr.emifer3dcalc.modules.cost.CostConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CostConfigInitializer implements CommandLineRunner {
    @Autowired
    private CostConfigRepository costConfigRepository;

    @Override
    public void run(String... args){
        if(costConfigRepository.findAll().isEmpty()){
            CostConfig config = CostConfig.builder()
                    .kwhCost(8)
                    .profitPercentage(20)
                    .build();
            costConfigRepository.save(config);
        }
    }
}
