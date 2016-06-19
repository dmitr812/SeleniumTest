package web_test;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import web_pages.BasePage;
import web_pages.SearchPage;
import web_pages.ResultPage;

public class YandexTest 
{
	WebDriver driver;		// объект класса WebDriver
	BasePage yBase;			// объект класса BasePage
	SearchPage ySearch;		// объект класса SearchPage
	ResultPage yResult;		// объект класса ResultPage
	
	String url = "http://www.yandex.ru"; 		// адрес web-страницы
	
 	@Before							// выполняется перед каждым тестом
	public void setUp() throws Exception 
	{
 		driver = new FirefoxDriver(DesiredCapabilities.firefox());		// инициализирует driver браузера
 		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);// таймаут ожидания загрузки
		driver.get (url);												// открывает web-страницу
        driver.manage().window().maximize();  							// раскрывает окна браузера на весь экран 
    }

	@Test
	public void testCase1()  
	{
		System.out.println("------ TestCase #1 ------");
		yBase = new BasePage(driver);			// инициализация объекта web-страницы с параметром driver
		yBase.beginNavigate(1);					// переходы к началу поиска
		
		ySearch = new SearchPage(driver);		// инициализация объекта web-страницы 
		ySearch.Searching1();					// настройка параметров поиска
		
		yResult = new ResultPage(driver);		// инициализация объекта web-страницы 	
		assertTrue("Проверка количества ", yResult.listSizeCheck()); // проверка количества элементов в списке
		assertTrue("Проверка перехода ", yResult.pageCheck());	// проверка правильности перехода на страницу товара
	}
	
	@Test
	public void testCase2() 
	{
		System.out.println("------ TestCase #2 ------");
		yBase = new BasePage(driver);			// инициализация объекта web-страницы с параметром driver
		yBase.beginNavigate(2);					// переходы к началу поиска				
		
		ySearch = new SearchPage(driver);		// инициализация объекта web-страницы 
		ySearch.Searching2();					// настройка параметров поиска		
		
		yResult = new ResultPage(driver);		// инициализация объекта web-страницы 	
		assertTrue("Проверка количества ", yResult.listSizeCheck()); // проверка количества элементов в списке
		assertTrue("Проверка перехода ", yResult.pageCheck());	// проверка правильности перехода на страницу товара
	}
	
	@After							// выполняется после каждого теста
	public void tearDown() throws Exception 
	{
		driver.quit(); 			// закрывает браузер после выполнения теста
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
}
