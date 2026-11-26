import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.puzenko.lab3.exception.BuildingNotFoundException;
import ru.puzenko.lab3.model.Building;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {

    @Mock
    private BuildingRepository buildingRepository;

    @InjectMocks
    private BuildingServiceImpl buildingService;

    @Test
    void findById_shouldReturnBuilding_whenExists() {
        Building building = new Building(1, "Lenina 1", 5);
        when(buildingRepository.findById(1)).thenReturn(Optional.of(building));

        Building result = buildingService.findById(1);

        assertEquals("Lenina 1", result.getAddress());
        verify(buildingRepository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(buildingRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(BuildingNotFoundException.class, () -> buildingService.findById(99));
        verify(buildingRepository).findById(99);
    }

    @Test
    void save_shouldReturnId() {
        Building building = new Building(0, "New", 3);
        when(buildingRepository.save(building)).thenReturn(10);

        int id = buildingService.save(building);

        assertEquals(10, id);
        verify(buildingRepository).save(building);
    }

    @Test
    void update_shouldUpdate_whenExists() {
        Building building = new Building(1, "Updated", 6);
        when(buildingRepository.findById(1)).thenReturn(Optional.of(building));

        buildingService.update(building);

        verify(buildingRepository).update(building);
    }

    @Test
    void update_shouldThrowException_whenNotFound() {
        Building building = new Building(99, "Updated", 6);
        when(buildingRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(BuildingNotFoundException.class, () -> buildingService.update(building));
        verify(buildingRepository, never()).update(building);
    }

    @Test
    void deleteById_shouldCallRepository() {
        buildingService.deleteById(1);
        verify(buildingRepository).deleteById(1);
    }

    @Test
    void findAll_shouldReturnList() {
        List<Building> buildings = List.of(new Building(1, "A", 1), new Building(2, "B", 2));
        when(buildingRepository.findAll()).thenReturn(buildings);

        List<Building> result = buildingService.findAll();

        assertEquals(2, result.size());
        verify(buildingRepository).findAll();
    }
}