package by.itacademy.taskmanager.userservice.core.dto.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PageDTO<T> {

    @JsonProperty("number")
    private Integer number;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("total_elements")
    private Long totalElements;
    @JsonProperty("first")
    private Boolean first;
    @JsonProperty("number_of_elements")
    private Integer numberOfElements;
    @JsonProperty("last")
    private Boolean last;

    @JsonProperty("content")
    private List<T> content;
}
