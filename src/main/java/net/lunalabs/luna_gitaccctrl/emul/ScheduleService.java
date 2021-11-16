package net.lunalabs.luna_gitaccctrl.emul;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduleService {

    @Scheduled(initialDelay = 1000, fixedDelay = 3000)
    public void test(){

        log.info("Z");

    }

}
