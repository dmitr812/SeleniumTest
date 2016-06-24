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
	
	String marketLinkTitle = "Яндекс.Маркет — покупки в тысячах проверенных магазинов";	// title страницы 
	String computersLinkTitle = "Компьютерная техника — купить на Яндекс.Маркете";		// title страницы 
	String notebooksLinkTitle = "Ноутбуки - выбирайте и покупайте на Яндекс.Маркете";	// title страницы 
	String tabletLinkTitle = "Планшеты - выбирайте и покупайте на Яндекс.Маркете";		// title страницы 
	String searchLinkTitle = "— купить на Яндекс.Маркете";								// title страницы 
	
	protected WebDriver driver;				// объект класса WebDriver
			
	public BasePage(WebDriver driver)		// конструктор:  
	{										// передает driver в качестве параметра
		this.driver = driver;					
	}
	
	protected String clickLink(By link) 			// метод (доступен для дочерних классов)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);		// инициализация ожидания драйвера
																// ожидание элемента
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(link)); 								
		element.click();										// клик по элементу
		System.out.println("-> " + driver.getTitle());
		return driver.getTitle();								// возвращаем pageTitle
	}		
	
	protected void clickLink_(By link) 				// метод (доступен для дочерних классов)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);		// инициализация ожидания драйвера
																// ожидание элемента
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(link)); 	
		element.click();										// клик по элементу
																// ожидание refreshed-элемента
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(link)));	
		System.out.println("->> " + driver.getTitle());
	}		
	
	protected String getPageTitle() 			// метод (доступен для дочерних классов)
	{
		String title = driver.getTitle();		// читает page title
		System.out.println("->> " + title);
		return title;							// возвращает page title
	}
	
	public boolean goPage(int i)		 		// метод: возвращает true,  
	{											// если переход по ссылке корректен 
		String title = "";
		switch (i)
		{
			case 1:	title = clickLink(marketLink);			// переход по клику по ссылке 
					return (title.equals(marketLinkTitle));
			case 2:	title = clickLink(computersLink);		// переход по клику по ссылке 
					return (title.equals(computersLinkTitle));
			case 3:	title = clickLink(notebooksLink);		// переход по клику по ссылке 
					return (title.equals(notebooksLinkTitle));
			case 4:	title = clickLink(tabletLink);			// переход по клику по ссылке 
					return (title.equals(tabletLinkTitle));
			case 5:	title = clickLink(searchLink);			// переход по клику по ссылке 
					return (title.contains(searchLinkTitle));
			default: 
					return false;
		}
	}
		
	
	
}


