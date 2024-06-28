package com.example.hobbysky;

import com.example.hobbysky.dto.ProfileDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.model.Profile;
import com.example.hobbysky.model.User;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTests {

    @Test
    public void testGettersAndSetters() {
        Profile profile = new Profile();

        Long id = 1L;
        String description = "This is a description";
        String image = "image.png";
        User user = new User();
        Date creationDate = new Date();
        Date lastModifiedDate = new Date();

        profile.setId(id);
        profile.setDescription(description);
        profile.setImage(image);
        profile.setUser(user);
        profile.setCreationDate(creationDate);
        profile.setLastModifiedDate(lastModifiedDate);

        assertEquals(id, profile.getId());
        assertEquals(description, profile.getDescription());
        assertEquals(image, profile.getImage());
        assertEquals(user, profile.getUser());
        assertEquals(creationDate, profile.getCreationDate());
        assertEquals(lastModifiedDate, profile.getLastModifiedDate());
    }

    @Test
    public void testDefaultConstructor() {
        Profile profile = new Profile();
        assertNull(profile.getId());
        assertNull(profile.getDescription());
        assertNull(profile.getImage());
        assertNull(profile.getUser());
        assertNull(profile.getCreationDate());
        assertNull(profile.getLastModifiedDate());
    }
    @Test
    public void testGettersAndSettersDTO() {
        ProfileDTO profileDTO = new ProfileDTO();

        Long id = 1L;
        String description = "This is a description";
        String image = "image.png";
        UserDTO user = new UserDTO();
        Date creationDate = new Date();
        Date lastModifiedDate = new Date();

        profileDTO.setId(id);
        profileDTO.setDescription(description);
        profileDTO.setImage(image);
        profileDTO.setUser(user);
        profileDTO.setCreationDate(creationDate);
        profileDTO.setLastModifiedDate(lastModifiedDate);

        assertEquals(id, profileDTO.getId());
        assertEquals(description, profileDTO.getDescription());
        assertEquals(image, profileDTO.getImage());
        assertEquals(user, profileDTO.getUser());
        assertEquals(creationDate, profileDTO.getCreationDate());
        assertEquals(lastModifiedDate, profileDTO.getLastModifiedDate());
    }

    @Test
    public void testDefaultConstructorDTO() {
        ProfileDTO profileDTO = new ProfileDTO();
        assertNull(profileDTO.getId());
        assertNull(profileDTO.getDescription());
        assertNull(profileDTO.getImage());
        assertNull(profileDTO.getUser());
        assertNull(profileDTO.getCreationDate());
        assertNull(profileDTO.getLastModifiedDate());
    }
}
