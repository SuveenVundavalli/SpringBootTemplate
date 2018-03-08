package me.suveen.portfolio;

import me.suveen.portfolio.web.i18n.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioApplicationTests {

	@Autowired
	private I18NService i18NService;

	@Test
	public void testMessageByLocaleService() {
		String expectedResult = "Suveen Kumar Vundavalli";
		String messageId = "index.h1.text";
		String actualResult = i18NService.getMessage(messageId);
		Assert.assertEquals("testMessageByLocaleService", expectedResult, actualResult);
	}

}
