package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

@Path("animals")
public class AnimalsDemo {

    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalsDemo
     */
    public AnimalsDemo() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalsDemo
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "Hallo... I mean... Vuf";
    }

    /**
     * PUT method for updating or creating an instance of AnimalsDemo
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @Path("animal_list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String animals() {
        String dog = "Dog";
        String cat = "Cat";
        String mouse = "Mouse";
        String bird = "Bird";
        List<String> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(cat);
        animals.add(mouse);
        animals.add(bird);
        return GSON.toJson(animals);
    }

    @Path("animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String animalsNoDB() {
        AnimalNoDB animal = new AnimalNoDB();
        return new Gson().toJson(animal);
    }
}
