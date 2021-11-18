package net.lunalabs.luna_gitaccctrl.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lunalabs.luna_gitaccctrl.emul.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final ScheduleService scheduleService;


    @GetMapping("/receive")
    public void registerReceive(String msg){

        log.info("ip 등록되었다고 메시지 받음.. :" + msg);

        scheduleService.natCommandAction();

    }



}
