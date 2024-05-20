package expeditors.backend.adoption.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import expeditors.backend.adoption.classes.TypePet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AdopterServiceMockMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetAll() throws Exception {
        MockHttpServletRequestBuilder builder = get("/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());

    }

    @Test
    public void testGetOneWithGoodId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOneWithBadId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/petservice/{id}", 1000)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testPostAdopter() throws Exception {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        String jsonString = mapper.writeValueAsString(adopter);

        ResultActions actions = mockMvc.perform(post("/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString));

        actions = actions.andExpect(status().isCreated());

        MvcResult result = actions.andReturn();
        String locHeader = result.getResponse().getHeader("Location");
        assertNotNull(locHeader);
        System.out.println("locHeader: " + locHeader);
    }

    @Test
    public void testDeleteAdopterWithGoodId() throws Exception {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        String jsonString = mapper.writeValueAsString(adopter);

        ResultActions actions = mockMvc.perform(post("/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString));

        actions = actions.andExpect(status().isCreated());

        MvcResult result = actions.andReturn();
        String locHeader = result.getResponse().getHeader("Location");
        assertNotNull(locHeader);

        actions = mockMvc.perform(delete(locHeader)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
    }

    @Test
    public void testDeleteAdopterWithBadId() throws Exception {
        ResultActions actions = mockMvc.perform(delete("/adopter/{id}", 999)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testUpdateAdopterWithGoodId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
        MvcResult result = actions.andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        JsonNode node = mapper.readTree(jsonResult);
        Adopter adopter = mapper.treeToValue(node, Adopter.class);
        adopter.setName("Luz");

        String updatedJson = mapper.writeValueAsString(adopter);

        actions = mockMvc.perform(put("/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson));

        actions = actions.andExpect(status().isOk());

        builder = get("/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
        result = actions.andReturn();

        jsonResult = result.getResponse().getContentAsString();
        node = mapper.readTree(jsonResult);
        adopter = mapper.treeToValue(node, Adopter.class);

        assertEquals("Luz", adopter.getName());
    }

    @Test
    public void testUpdateAdopterWithBadId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
        MvcResult result = actions.andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        JsonNode node = mapper.readTree(jsonResult);
        Adopter adopter = mapper.treeToValue(node, Adopter.class);
        adopter.setName("Luz");
        adopter.setId(999);

        String updatedJson = mapper.writeValueAsString(adopter);

        actions = mockMvc.perform(put("/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson));

        actions = actions.andExpect(status().isNotFound());
    }
}
