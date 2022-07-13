package stdbtt.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stdbtt.springcourse.FirstRestApp.models.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    public List<Measurement> findAllByRainingIsTrue();

    public int countMeasurementByRainingIsTrue();
}
