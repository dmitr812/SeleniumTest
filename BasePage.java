package web_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage 		// базовый класс объектов web-страниц
{

	By marketLink = By.linkText("Маркет");					// локатор текстовой ссылки
	By computersLink = By.linkText("Компьютеры");			// локатор текстовой ссылки
	By notebooksLink = By.linkText("Ноутбуки");				// локатор текстовой ссылки
	By tabletLink = By.linkText("Планшеты");				// локатор текстовой ссылки
	By searchLink = By.partialLinkText("Расширенный");		// локатор текстовой ссылки
	
	protected WebDriver driver;					// объект класса WebDriver
			
	public BasePage(WebDriver driver)			// конструктор:  
	{											// передает driver в качестве параметра
		this.driver = driver;					
	}
	
	protected String getPageTitle() 			// метод (доступен для дочерних классов)
	{
		String title = driver.getTitle();		// читает page title
		System.out.println("Title страницы: " + title);
		return title;							// возвращает page title
	}
	
	protected void clickLink(By link) 		// метод (доступен для дочерних классов)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);	// инициализация ожидания драйвера
															// ожидание видимости элемента
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(link)); 
		element.click();									// клик по элементу
		System.out.println("pageTitle: " + driver.getTitle());			
	}		
	
	public void beginNavigate(int caseNumber) 		// метод: переходы к началу поиска
	{
		clickLink(marketLink);				// переход по клику по ссылке 
		clickLink(computersLink);			// переход по клику по ссылке
		if (caseNumber == 1)
			clickLink(notebooksLink);		// переход по клику по ссылке 
		else if (caseNumber == 2)
			clickLink(tabletLink);			// переход по клику по ссылке
		else
			System.out.println("BasePage: invalid testCase");
		clickLink(searchLink);				// переход по клику по ссылке
	}
	
}
