package pl.sdacademy.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@Entity
public class Movie extends AbstractEntity {

    private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Actor> actors;

    public Movie(String title, Set<Actor> actors) {
        this.title = title;
        this.actors = actors;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
