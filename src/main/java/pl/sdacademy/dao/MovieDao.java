package pl.sdacademy.dao;

import org.hibernate.Hibernate;
import pl.sdacademy.entity.Actor;
import pl.sdacademy.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class MovieDao extends EntityDao<Movie> {


    public MovieDao() {
        super(Movie.class);
    }

    public void initializeLazyField(Movie entity) {
        Hibernate.initialize(entity.getActors());
    }

    @Override
    @Transactional
    public Movie findById(int id) {
        Movie movie = super.findById(id);
        initializeLazyField(movie);
        return movie;
    }
}
