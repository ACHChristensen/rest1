package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import static javax.swing.text.html.HTML.Attribute.ID;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("animals_db")
public class AnimalsFromDB {

    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalsFromDB
     */
    public AnimalsFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalsFromDB
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Animal animal = em.find(Animal.class, id);
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }

    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type =:type", Animal.class);
            query.setParameter("type", type);
            Animal animal = query.getSingleResult();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }

    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsByRandom() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM ANIMAL a ORDER BY RAND() LIMIT 1", Animal.class);
//            int qntyMax = query.getMaxResults();
//            Random random = new Random();
//            int randomID = random.nextInt(qntyMax)+1;
//            Animal randomAnimal = em.find(Animal.class, randomID);
            Animal randAnimal = query.getSingleResult();
            return new Gson().toJson(randAnimal);
        } finally {
            em.close();
        }
    }
}
