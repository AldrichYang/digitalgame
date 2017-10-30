package digitalgame;


import digitalgame.dao.OpenInfoMapper;
import digitalgame.model.po.OpenInfo;
import digitalgame.model.po.UserBetInfo;
import digitalgame.service.GuessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalgameApplicationTests {

	@Autowired
	private GuessService guessService;

	@Autowired
	private OpenInfoMapper oim ;

	@Test
	public void contextLoads() {

	}

//	@Test
//	public void betTest(){
//		List<UserBetInfo> userBetInfos = guessService.analysisBetContent("张三 15:27:00 \ndfdafqqqq300\n答复qqqq200dfa100\n张三答复 15:27:00\ndfdafqqqq3000");
//		assertTrue(userBetInfos.size()==2);
//
//		//guessService.doBet(betInfos);
//	}

	@Test
	public void openTest(){
//	    String openNo = guessService.getNextOpenNo();
//	    assertTrue(openNo != null);
//	    guessService.doOpen(openNo,"123");
//		guessService.doOpen(openNo,"578");
		List<OpenInfo> openInfoList = oim.selectTop();
		assertTrue(openInfoList != null);
    }

}
