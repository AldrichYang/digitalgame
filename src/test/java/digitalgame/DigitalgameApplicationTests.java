package digitalgame;

import digitalgame.service.GuessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalgameApplicationTests {

	@Autowired
	private GuessService guessService;

//	@Test
//	public void contextLoads() {
//
//	}
//
//	@Test
//	public void betTest(){
//		List<UserBetInfo> userBetInfos = guessService.analysisBetContent("张三 15:27:00 \ndfdafqqqq300\n答复qqqq200dfa100\n张三答复 15:27:00\ndfdafqqqq3000");
//		assertTrue(userBetInfos.size()==2);
//
//		//guessService.doBet(betInfos);
//
//	}

	@Test
	public void normalTest(){
		String a = "3";
		String b = "12";

		System.out.printf(String.format("%03d",3));
		System.out.printf(String.format("%03d",12));
	}


}
