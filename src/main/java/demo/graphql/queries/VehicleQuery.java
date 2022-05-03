package demo.graphql.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import demo.repositories.entities.Vehicle;
import demo.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleQuery implements GraphQLQueryResolver {
    private final VehicleService vehicleService;

    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleService.findById(id);
    }
}