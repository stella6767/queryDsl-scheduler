package net.lunalabs.luna_gitaccctrl.emul;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lunalabs.luna_gitaccctrl.domain.LunaGitOauth;
import net.lunalabs.luna_gitaccctrl.domain.LunaGitOauthRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final LunaGitOauthRepository lunaGitOauthRepository;


    @Transactional //UPDATE, DELETE 경우 @Transactional을 추가해줘야 에러가 발생 X
    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void natCommandAction(){

        //전체는 찾지말고, procYn = n인 애들만 찾아서. y로 바꿔주자..

        List<LunaGitOauth> lunaGitOauths  = lunaGitOauthRepository.findLunaGitOauthsProcYNIsN();
        //List<LunaGitOauth> lunaGitOauths  = lunaGitOauthRepository.mFindByProcyYnIsN();

        log.info("5초 간격 지속적 폴링");

        for (LunaGitOauth lunaGitOauth: lunaGitOauths) {
            log.info(lunaGitOauth.toString());
            
            commandLine(lunaGitOauth.getIp());
            
            Long result = lunaGitOauthRepository.updateLunaGitOauthsProcYNIsY(lunaGitOauth.getId());
            log.info("update 결과: " + result);
            //업데이트를 하나씩 계속 하기보다 한 번에 하는 게 트랜잭션 에서 유리해보이는데, 일단 이렇게 하라고
            //시켰으니 이렇게 하겠습니다.
        }
    }

    private void commandLine(String ipAddr) {
    	System.out.println("Hello, World!");
        String s;
        Process p;
        try {
        	//이 변수에 명령어를 넣어주면 된다. 
            String[] cmd = {"/usr/bin/sudo", "/sbin/iptables"
            		, "-t", "nat",  "-A",  "GIT_NAT", "-p", "tcp"
            		, "-s", ipAddr, "--dport", "443", "-j", "DNAT", "--to"
            		, "172.16.81.212:443"};
            p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println(s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        }
    }
}
