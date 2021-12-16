package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepositoryMock;

    @InjectMocks
    private CityService cityService;

    @Test
    public void getAll_testSuccess() {

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City(2, "Karongi", 24, 70),
                new City(3, "Rubavu", 26, 10)));
        assertEquals(75.2, cityService.getAll().get(0).getFahrenheit());
    }

    @Test
    public void getById_testSuccess() {
        City city = new City(104l, "Kigali", 10, 50);
        when(cityRepositoryMock.findById(104l)).thenReturn(Optional.of(city));
        assertEquals(50, cityService.getById(104l).getFahrenheit());
    }

    @Test
    public void getById_testFailure() {
        when(cityRepositoryMock.findById(111l)).thenReturn(Optional.empty());
        City city = cityService.getById(111l);
        assertTrue(city == null);
    }

    @Test
    public void save_success() {
        CreateCityDTO dto = new CreateCityDTO();
        dto.setName("Kigali");
        dto.setWeather(24);
        City city = new City(dto.getName(), dto.getWeather());
        when(cityRepositoryMock.save(city)).thenReturn(city);
        City createCity = cityService.save(dto);
        assertTrue(createCity.getName() == "Kigali");
    }

    @Test
    public void existsByName_test() {
        when(cityRepositoryMock.existsByName(anyString())).thenReturn(true);
        assertTrue(cityService.existsByName("Musanze"));
    }

    @Test
    public void toFahrenheit_testGivenZero(){
        assertEquals(32, cityService.caclWeatherInFahrenheit(0));
    }
    @Test
    public void toFahrenheit_testGivenAny(){
        assertEquals(68, cityService.caclWeatherInFahrenheit(20));
    }


}
