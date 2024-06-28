package com.example.hobbysky;

import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.model.Hobby;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HobbyTests {

    @Test
    public void testGetId() {
        Hobby hobby = new Hobby();
        hobby.setId(1L);
        assertEquals(1L, hobby.getId());
    }

    @Test
    public void testSetId() {
        Hobby hobby = new Hobby();
        hobby.setId(2L);
        assertEquals(2L, hobby.getId());
    }

    @Test
    public void testGetName() {
        Hobby hobby = new Hobby();
        hobby.setName("Reading");
        assertEquals("Reading", hobby.getName());
    }

    @Test
    public void testSetName() {
        Hobby hobby = new Hobby();
        hobby.setName("Swimming");
        assertEquals("Swimming", hobby.getName());
    }

    @Test
    public void testConstructor() {
        Hobby hobby = new Hobby();
        assertNull(hobby.getId());
        assertNull(hobby.getName());
    }

    @Test
    public void testToString() {
        Hobby hobby = new Hobby();
        hobby.setId(1L);
        hobby.setName("Reading");
        assertEquals("Hobby(id=1, name=Reading)", hobby.toString());
    }

    @Test
    public void testGetIdDto() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(1L);
        assertEquals(1L, hobbyDTO.getId());
    }

    @Test
    public void testSetIdDto() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(2L);
        assertEquals(2L, hobbyDTO.getId());
    }

    @Test
    public void testGetNameDto() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setName("Reading");
        assertEquals("Reading", hobbyDTO.getName());
    }

    @Test
    public void testSetNameDto() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setName("Swimming");
        assertEquals("Swimming", hobbyDTO.getName());
    }

    @Test
    public void testConstructorDto() {
        HobbyDTO hobbyDTO = new HobbyDTO("1", "Reading");
        assertNotNull(hobbyDTO);
    }

    @Test
    public void testToStringDto() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(1L);
        hobbyDTO.setName("Reading");
        assertEquals("HobbyDTO(id=1, name=Reading)", hobbyDTO.toString());
    }

    @Test
    public void testEquals() {
        HobbyDTO hobbyDTO1 = new HobbyDTO();
        hobbyDTO1.setId(1L);
        hobbyDTO1.setName("Reading");

        HobbyDTO hobbyDTO2 = new HobbyDTO();
        hobbyDTO2.setId(1L);
        hobbyDTO2.setName("Reading");

        assertEquals(hobbyDTO1, hobbyDTO2);
        assertEquals(hobbyDTO1.hashCode(), hobbyDTO2.hashCode());
    }

    @Test
    public void testNotEquals() {
        HobbyDTO hobbyDTO1 = new HobbyDTO();
        hobbyDTO1.setId(1L);
        hobbyDTO1.setName("Reading");

        HobbyDTO hobbyDTO2 = new HobbyDTO();
        hobbyDTO2.setId(2L);
        hobbyDTO2.setName("Swimming");

        assertNotEquals(hobbyDTO1, hobbyDTO2);
        assertNotEquals(hobbyDTO1.hashCode(), hobbyDTO2.hashCode());
    }

    @Test
    public void testCanEqual() {
        HobbyDTO hobbyDTO1 = new HobbyDTO();
        HobbyDTO hobbyDTO2 = new HobbyDTO();

        assertTrue(hobbyDTO1.canEqual(hobbyDTO2));
    }
}
