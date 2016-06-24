package web_test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	List<WebElement> foundElements;				// список web-элементов 
		
 	@Before					// выполняется перед каждым тестом
 	public void setUp() throws Exception 
	{
 		driver = new FirefoxDriver(DesiredCapabilities.firefox());	// инициализирует driver браузера
 		driver.get (url);											// открывает web-страницу
        driver.manage().window().maximize();  						// раскрывает окна браузера на весь экран 
        foundElements = null;										// инициализация списка web-элементов 
    }

	@Test
	public void testCase1()  
	{
		System.out.println("------ TestCase #1 ------");
		yBase = new BasePage(driver);			// инициализация объекта web-страницы с параметром driver
		assertTrue("#1 toPage->1", yBase.goPage(1)); 	// проверка перехода
		assertTrue("#1 toPage->2", yBase.goPage(2)); 	// проверка перехода
		assertTrue("#1 toPage->3", yBase.goPage(3)); 	// проверка перехода
		assertTrue("#1 toPage->5", yBase.goPage(5)); 	// проверка перехода
				
		ySearch = new SearchPage(driver);		// инициализация объекта web-страницы 
		ySearch.Searching1();					// настройка параметров поиска
		
		yResult = new ResultPage(driver);		// инициализация объекта web-страницы 
		foundElements = yResult.getList();		// получает список элементов
		assertTrue("#1: list is null ", foundElements != null); // проверка, что спиcок != null
		assertTrue("#1: listSize !=n ", yResult.listSizeCheck(foundElements)); 	// проверка количества элементов в списке
		assertTrue("#1: ->finalPage  ", yResult.pageCheck(foundElements));	// проверка правильности перехода на страницу товара
	}
	
	@Test
	public void testCase2() 
	{
		System.out.println("------ TestCase #2 ------");
		yBase = new BasePage(driver);	// инициализация объекта web-страницы с параметром driver
		assertTrue("#2 toPage->1", yBase.goPage(1)); 	// проверка перехода
		assertTrue("#2 toPage->2", yBase.goPage(2)); 	// проверка перехода
		assertTrue("#2 toPage->4", yBase.goPage(4)); 	// проверка перехода
		assertTrue("#2 toPage->5", yBase.goPage(5)); 	// проверка перехода
				
		ySearch = new SearchPage(driver);		// инициализация объекта web-страницы 
		ySearch.Searching2();					// настройка параметров поиска		
		
		yResult = new ResultPage(driver);		// инициализация объекта web-страницы 	
		foundElements = yResult.getList();		// получает список элементов
		assertTrue("#2: list is null ", foundElements != null); // проверка, что спиcок != null
		assertTrue("#2: listSize !=n ", yResult.listSizeCheck(foundElements)); 	// проверка количества элементов в списке
		assertTrue("#2: ->finalPage  ", yResult.pageCheck(foundElements));	// проверка правильности перехода на страницу товара
	}
			
	@After						// выполняется после каждого теста
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


