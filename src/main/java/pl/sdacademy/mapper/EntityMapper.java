package pl.sdacademy.mapper;


import pl.sdacademy.dto.RequestDto;
import pl.sdacademy.dto.ResponseDto;
import pl.sdacademy.entity.AbstractEntity;

/*2. Stwórz interfejs EntityMapper w pakiecie mapper - typ generyczny o trzech typach sparametryzowanych -
        pierwszy (REQUEST_DTO to typ implementujący interfejs RequestDto, drugi to typ implementujący ResponseDto, trzeci to typ rozszerzający AbstractEntity).
        Oferuje metody - mapToEntity (argument to obiekt typu REQUEST_DTO), zwraca obiekt typu ENTITY. Oraz metodę mapToResponseDto (argument to encja, zwraca RESPONSE_DTO).*/
public interface EntityMapper<S extends RequestDto, T extends ResponseDto, V extends AbstractEntity> {

    V mapToEntity(S requestDto);
    T mapToResponseDto(V entity);
}
