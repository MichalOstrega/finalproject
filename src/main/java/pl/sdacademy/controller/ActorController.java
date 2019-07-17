package pl.sdacademy.controller;

import pl.sdacademy.dao.ActorDao;
import pl.sdacademy.dto.ActorDto;
import pl.sdacademy.entity.Actor;
import pl.sdacademy.mapper.ActorMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/actor")
@Produces(MediaType.APPLICATION_JSON)
public class ActorController {

    @Inject
    private ActorDao actorDao;

    @Inject
    private ActorMapper actorMapper;

    @GET
    public Collection<ActorDto> getAll() {
        return actorDao.findAll().stream().map(actorMapper::mapToResponseDto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public ActorDto getActor(@PathParam("id") Integer id) {
        Actor actorFromDb = actorDao.findById(id);
        if (actorFromDb != null) {
            return actorMapper.mapToResponseDto(actorFromDb);
        } else {
            throw new NotFoundException();
        }

    }

    @DELETE
    @Path("/{id}")
    public ActorDto delete(@PathParam("id") Integer id){
        Actor actorFromDB = actorDao.findById(id);
        if (actorFromDB != null) {
            actorDao.deleteById(id);
            return actorMapper.mapToResponseDto(actorFromDB);
        }
        else {
            throw new NotFoundException();
        }
    }

    @POST
    public ActorDto postActor(ActorDto actorDto) {
        if (actorDto.getId() !=null) {
            actorDto.setId(null);
        }
        Actor actor = actorMapper.mapToEntity(actorDto);
        return actorMapper.mapToResponseDto(actor);
    }

    @PUT
    @Path("/{id}")
    public ActorDto putActor(@PathParam("id") Integer id, ActorDto actorDto) {
        actorDto.setId(id);
        Actor actor = actorMapper.mapToEntity(actorDto);
        return actorMapper.mapToResponseDto(actor);
    }


}
