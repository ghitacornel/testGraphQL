package demo.graphql.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import demo.repositories.entities.Vehicle;
import demo.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMutation implements GraphQLMutationResolver {
    private final VehicleService service;

    public Vehicle createVehicle(String brandName, String modelCode, String type) {
        return service.createVehicle(type, modelCode, brandName);
    }

    public Long removeVehicle(Long id) {
        service.removeVehicle(id);
        return id;
    }
}