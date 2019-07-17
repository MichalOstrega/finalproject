package pl.sdacademy.dao;

import pl.sdacademy.entity.Actor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActorDao extends EntityDao<Actor> {


    public ActorDao() {
        super(Actor.class);
    }
}
