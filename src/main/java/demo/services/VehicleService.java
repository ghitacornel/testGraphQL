package demo.services;

import demo.repositories.VehicleRepository;
import demo.repositories.entities.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle createVehicle(String type, String modelCode, String brandName) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }
}