package org.zerock.apiserver.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

//비닐봉지 일회용컵 아무도 신경안씀.
public class TodoDTO {

    private Long tno;

    private String title;
    private String content;
    private boolean complete;
    private LocalDate dueDate;

}
