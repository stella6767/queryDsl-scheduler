package net.lunalabs.luna_gitaccctrl.emul;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lunalabs.luna_gitaccctrl.domain.LunaGitOauth;
import net.lunalabs.luna_gitaccctrl.domain.LunaGitOauthRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final LunaGitOauthRepository lunaGitOauthRepository;


    @Scheduled(initialDelay = 1000, fixedDelay = 3000)
    public void test(){

        List<LunaGitOauth> lunaGitOauths  = lunaGitOauthRepository.findAll();

        log.info("3초 간격 지속적 폴링: "   + lunaGitOauths);

    }

}
