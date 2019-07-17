package pl.sdacademy.controller;

import pl.sdacademy.dao.MovieDao;
import pl.sdacademy.dto.MovieRequestDto;
import pl.sdacademy.dto.MovieResponseDto;
import pl.sdacademy.entity.Movie;
import pl.sdacademy.mapper.MovieMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    private MovieDao movieDao;

    @Inject
    private MovieMapper movieMapper;

    @GET
    public Collection<MovieResponseDto> getAll() {
        return movieDao.findAll().stream().map(movieMapper::mapToResponseDto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public MovieResponseDto getMovie(@PathParam("id") Integer id) {
        Movie movieFromDb = movieDao.findById(id);
        if (movieFromDb != null) {
            return movieMapper.mapToResponseDto(movieFromDb);
        } else {
            throw new NotFoundException();
        }

    }

    @DELETE
    @Path("/{id}")
    public MovieResponseDto delete(@PathParam("id") Integer id){
        Movie movieFromDB = movieDao.findById(id);
        if (movieFromDB != null) {
            movieDao.deleteById(id);
            return movieMapper.mapToResponseDto(movieFromDB);
        }
        else {
            throw new NotFoundException();
        }
    }

    @POST
    public MovieResponseDto postMovie(MovieRequestDto movieRequestDto) {
        if (movieRequestDto.getId() !=null) {
            movieRequestDto.setId(null);
        }
        Movie movie = movieMapper.mapToEntity(movieRequestDto);
        return movieMapper.mapToResponseDto(movie);
    }

    @PUT
    @Path("/{id}")
    public MovieResponseDto putMovie(@PathParam("id") Integer id, MovieRequestDto movieRequestDto) {
        movieRequestDto.setId(id);
        Movie movie = movieMapper.mapToEntity(movieRequestDto);
        return movieMapper.mapToResponseDto(movie);
    }


}
