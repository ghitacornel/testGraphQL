package demo.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import demo.daos.entities.Vehicle;
import demo.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleQuery implements GraphQLQueryResolver {
    private final VehicleService vehicleService;

    public List<Vehicle> vehicles() {
        return vehicleService.getAllVehicles();
    }

    public Optional<Vehicle> vehicle(Long id) {
        return vehicleService.getVehicle(id);
    }
}