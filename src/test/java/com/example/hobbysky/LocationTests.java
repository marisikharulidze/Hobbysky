package com.example.hobbysky;

import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.model.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTests {

    @Test
    public void testGetId() {
        Location location = new Location();
        location.setId(1L);
        assertEquals(1L, location.getId());
    }

    @Test
    public void testSetId() {
        Location location = new Location();
        location.setId(2L);
        assertEquals(2L, location.getId());
    }

    @Test
    public void testGetCountry() {
        Location location = new Location();
        location.setCountry("Canada");
        assertEquals("Canada", location.getCountry());
    }

    @Test
    public void testSetCountry() {
        Location location = new Location();
        location.setCountry("USA");
        assertEquals("USA", location.getCountry());
    }

    @Test
    public void testGetCity() {
        Location location = new Location();
        location.setCity("Toronto");
        assertEquals("Toronto", location.getCity());
    }

    @Test
    public void testSetCity() {
        Location location = new Location();
        location.setCity("New York");
        assertEquals("New York", location.getCity());
    }

    @Test
    public void testConstructor() {
        Location location = new Location();
        assertNull(location.getId());
        assertNull(location.getCountry());
        assertNull(location.getCity());
    }

    @Test
    public void testGetIdDto() {
        LocationDTO locationDTO = new LocationDTO("1", "Canada", "Toronto");
        locationDTO.setId(1L);
        assertEquals(1L, locationDTO.getId());
    }

    @Test
    public void testSetIdDto() {
        LocationDTO locationDTO = new LocationDTO("2", "USA", "New York");
        locationDTO.setId(2L);
        assertEquals(2L, locationDTO.getId());
    }

    @Test
    public void testGetCountryDto() {
        LocationDTO locationDTO = new LocationDTO("3", "Canada", "Toronto");
        locationDTO.setCountry("Canada");
        assertEquals("Canada", locationDTO.getCountry());
    }

    @Test
    public void testSetCountryDto() {
        LocationDTO locationDTO = new LocationDTO("4", "USA", "New York");
        locationDTO.setCountry("USA");
        assertEquals("USA", locationDTO.getCountry());
    }

    @Test
    public void testGetCityDto() {
        LocationDTO locationDTO = new LocationDTO("5", "Canada", "Toronto");
        locationDTO.setCity("Toronto");
        assertEquals("Toronto", locationDTO.getCity());
    }

    @Test
    public void testSetCityDto() {
        LocationDTO locationDTO = new LocationDTO("6", "USA", "New York");
        locationDTO.setCity("New York");
        assertEquals("New York", locationDTO.getCity());
    }

    @Test
    public void testConstructorDto() {
        LocationDTO locationDTO = new LocationDTO("7", "Canada", "Toronto");
        assertNotNull(locationDTO);
    }
}
