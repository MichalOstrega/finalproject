package pl.sdacademy.dto;

import java.util.Collection;

public class MovieResponseDto implements ResponseDto {

    private Integer id;
    private String title;
    private Collection<ActorDto> actors;

    public MovieResponseDto(Integer id, String title, Collection<ActorDto> actors) {
        this.id = id;
        this.title = title;
        this.actors = actors;
    }

    public MovieResponseDto() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<ActorDto> getActors() {
        return actors;
    }

    public void setActors(Collection<ActorDto> actors) {
        this.actors = actors;
    }
}
