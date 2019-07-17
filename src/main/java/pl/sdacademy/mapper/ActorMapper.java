package pl.sdacademy.mapper;

import pl.sdacademy.dao.ActorDao;
import pl.sdacademy.dto.ActorDto;
import pl.sdacademy.dto.RequestDto;
import pl.sdacademy.dto.ResponseDto;
import pl.sdacademy.entity.Actor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.awt.image.RescaleOp;

@ApplicationScoped
public class ActorMapper implements EntityMapper<ActorDto, ActorDto, Actor> {

    @Inject
    private ActorDao actorDao;


    @Override
    public Actor mapToEntity(ActorDto requestDto) {
        Integer id = requestDto.getId();
        if (id != null) {
            Actor actorFromDb = actorDao.findById(id);
            if (actorFromDb == null) {
                throw new NotFoundException();
            } else {
                actorFromDb.setFirstName(requestDto.getFirstName());
                actorFromDb.setLastName(requestDto.getLastName());
                actorDao.update(actorFromDb);
                return actorFromDb;
            }
        } else {
            Actor actor = new Actor(requestDto.getFirstName(), requestDto.getLastName());
            actorDao.save(actor);
            return actor;

        }
    }

    @Override
    public ActorDto mapToResponseDto(Actor entity) {
        if (entity != null) {
            return new ActorDto(entity.getId(), entity.getFirstName(), entity.getLastName());
        }
        return null;
    }
}
