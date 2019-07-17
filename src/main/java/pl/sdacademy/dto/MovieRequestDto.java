package pl.sdacademy.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

public class MovieRequestDto implements RequestDto {
    private Integer id;
    private String title;
    private Collection<Integer> actorIds;

    public MovieRequestDto(Integer id, String title, Collection<Integer> actorIds) {
        this.id = id;
        this.title = title;
        this.actorIds = actorIds;
    }

    public MovieRequestDto() {
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

    public Collection<Integer> getActorIds() {
        return actorIds;
    }

    public void setActorIds(Collection<Integer> actorIds) {
        this.actorIds = actorIds;
    }
}
