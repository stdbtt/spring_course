package stdbtt.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stdbtt.springcourse.FirstRestApp.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public Sensor findSensorByName(String name);
}
