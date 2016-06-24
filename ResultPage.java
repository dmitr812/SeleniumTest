package web_pages; 

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage	extends BasePage		// класс объектов web-страниц
{
	By list = By.cssSelector(".snippet-card__header-link");	// локатор элементов списка
	By marketSearch = By.id("header-search");				// локатор поля поиска
 	int listSize = 10;										// число элементов в списке
 	
 	private WebDriver driver_;								// объект класса WebDriver
 	 	
	public ResultPage(WebDriver driver) 	// конструктор:
	{										// передает driver в качестве параметра				
		super(driver);					// вызов конструктора суперкласса
		driver_ = super.driver; 		// обращается к driver из суперкласса
	}
	
	private List<WebElement> getWebList(By list) 		// метод: возвращает список элементов 
	{	
		try {	Thread.sleep(2000);} 
		catch (InterruptedException e)	
			{	e.printStackTrace();}
		WebDriverWait wait = new WebDriverWait(driver_, 10);// инициализация ожидания драйвера
		List<WebElement> listElements = wait.				// ожидание видимости всех элементов
			until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(list)));	
		return listElements;								// возвращает список элементов
	}
	
	private String getFirstText(List<WebElement> list) 	// метод: возвращает текст первого элемента списка  
	{
		WebElement element = list.get(0);			// получает первый элемент из списка
		String str1 = element.getText();			// получает текст элемента
		return str1;								// возвращает текст первого элемента
	}
	
	private void submitParam(By link, String str) 	// метод: отправляет форму
	{
		WebDriverWait wait = new WebDriverWait(driver_, 10);// инициализация ожидания драйвера
															// ожидание элемента
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(link)); 	
		element.sendKeys(str);								// ввод строки в форму
		element.submit();									// отправка формы
															// ожидание refreshed-элемента
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(link)));
	}
	
	public List<WebElement> getList() 					// метод: возвращает List<WebElement> 
	{											
		List<WebElement> foundElements = null;
		foundElements = getWebList(list);		// получает список элементов
		return (foundElements);					// возвращает список
	}
	
	public boolean listSizeCheck(List<WebElement> list) // метод: возвращает true,  
	{													// если число элементов списка = заданному 
		int n = list.size();					// получает число элементов в списке
		System.out.println("Elements in list: " + n);
		return (n == listSize);					// возвращает результат
	}
	
	public boolean pageCheck(List<WebElement> list) 	// метод: возвращает true,  
	{													// если переход на страницу товара правильный
		String str1 = getFirstText(list);		// получает текст первого элемента списка
		System.out.println("First in list: " + str1);
		
		submitParam(marketSearch, str1);		// ввод данных в форму и ее отправка
		String title = getPageTitle();			// получает page title
		return (title.contains(str1));			// возвращает результат
	}
	
}
