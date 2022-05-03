package demo.graphql.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import demo.repositories.entities.Vehicle;
import demo.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMutation implements GraphQLMutationResolver {
    private final VehicleService vehicleService;

    public Vehicle createVehicle(String modelCode, String type, String brandName) {
        return vehicleService.createVehicle(type, modelCode, brandName);
    }
}