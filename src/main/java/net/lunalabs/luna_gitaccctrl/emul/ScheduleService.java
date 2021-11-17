package net.lunalabs.luna_gitaccctrl.emul;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lunalabs.luna_gitaccctrl.domain.LunaGitOauth;
import net.lunalabs.luna_gitaccctrl.domain.LunaGitOauthRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final LunaGitOauthRepository lunaGitOauthRepository;


    @Transactional //UPDATE, DELETE 경우 @Transactional을 추가해줘야 에러가 발생 X
    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void test(){

        //전체는 찾지말고, procYn = n인 애들만 찾아서. y로 바꿔주자..

        List<LunaGitOauth> lunaGitOauths  = lunaGitOauthRepository.findLunaGitOauthsProcYNIsN();
        //List<LunaGitOauth> lunaGitOauths  = lunaGitOauthRepository.mFindByProcyYnIsN();

        log.info("5초 간격 지속적 폴링");

        for (LunaGitOauth lunaGitOauth: lunaGitOauths) {
            log.info(lunaGitOauth.toString());
            Long result = lunaGitOauthRepository.updateLunaGitOauthsProcYNIsY(lunaGitOauth.getId());
            log.info("update 결과: " + result);
            //업데이트를 하나씩 계속 하기보다 한 번에 하는 게 트랜잭션 에서 유리해보이는데, 일단 이렇게 하라고
            //시켰으니 이렇게 하겠습니다.
        }


    }



    public void 앞으로(){

        log.info(">>>>> 이제부터 뭐 할지는..");

    }






}
