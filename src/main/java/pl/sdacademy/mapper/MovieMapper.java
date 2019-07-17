package pl.sdacademy.mapper;

import org.hibernate.Hibernate;
import pl.sdacademy.dao.ActorDao;
import pl.sdacademy.dao.MovieDao;
import pl.sdacademy.dto.ActorDto;
import pl.sdacademy.dto.MovieRequestDto;
import pl.sdacademy.dto.MovieResponseDto;
import pl.sdacademy.entity.Actor;
import pl.sdacademy.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class MovieMapper implements EntityMapper<MovieRequestDto, MovieResponseDto, Movie> {

    @Inject
    private MovieDao movieDao;

    @Inject
    private ActorDao actorDao;

    @Inject
    private ActorMapper actorMapper;

    @Override
    public Movie mapToEntity(MovieRequestDto requestDto) {
        Integer id = requestDto.getId();
        if (id != null) {
            Movie movieFromDB = movieDao.findById(id);
            if (movieFromDB != null) {
                movieFromDB.setTitle(requestDto.getTitle());
                movieFromDB.setActors(requestDto.getActorIds().stream().map(actorDao::findById).collect(Collectors.toSet()));
                movieDao.update(movieFromDB);
                return movieFromDB;
            }
            else {
                throw new NotFoundException();
            }
        }
        else {
            Movie movie = new Movie(requestDto.getTitle(), requestDto.getActorIds().stream().map(actorDao::findById).collect(Collectors.toSet()));
            movieDao.save(movie);
            return movie;
        }
    }

    @Override
    public MovieResponseDto mapToResponseDto(Movie entity) {
        if (entity != null) {
            return new MovieResponseDto(entity.getId(),entity.getTitle(),entity.getActors().stream().map(actorMapper::mapToResponseDto).filter(Objects::nonNull).collect(Collectors.toList()));
        }
        else {
            return null;
        }
    }
}
