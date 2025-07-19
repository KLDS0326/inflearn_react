package org.zerock.apiserver.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.service.TodoService;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;


    @GetMapping("/{tno}")
    //주민번호 같이 변하지 않는 것은 PathVariable임.
    public TodoDTO get(@PathVariable("tno") Long tno) {
        return todoService.get(tno);
    }



    //변하는 것은 쿼리스트링으로 하면됨.
    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("LIST.....>" + pageRequestDTO);


        return todoService.getList(pageRequestDTO);

    }

    //들어가야하는 값이 제대로 안들어올 때 레스트 컨트롤러 어드바이스를 쓴다고함.
}
