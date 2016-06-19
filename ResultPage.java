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
		
	private List<WebElement> getList(By list) 		// метод 
	{											
		WebDriverWait wait = new WebDriverWait(driver_, 3);	// инициализация ожидания драйвера
		List<WebElement> listElements = null;		// инициализация списка элементов
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(list)); // ожидание элемента
		listElements = driver_.findElements(list);	// поиск  элементов
						
		return listElements;						// возвращает список элементов
	}
	
	private String getFirstText(List<WebElement> list) 	// метод 
	{
		WebElement element = list.get(0);			// получает первый элемент из списка
		String str1 = element.getText();			// получает текст элемента
		return str1;								// возвращает текст первого элемента
	}
	
	private void submitParam(By link, String str) 	// метод  
	{
		WebDriverWait wait = new WebDriverWait(driver_, 3);	// инициализация ожидания драйвера
															// ожидание элемента
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(link)); 
		element.sendKeys(str);								// ввод строки в форму
		element.submit();									// отправка формы		
	}
	
	public boolean listSizeCheck() 		// метод: возвращает true,  
	{									// если число элементов списка = заданному 
		List<WebElement> foundElements = null;
		foundElements = getList(list);				// получаем список элементов
		int n = foundElements.size();
		System.out.println("Количество элементов: " + n);
		if (n == listSize)
			return true;
		else 
			return false;
	}
	
	public boolean pageCheck() 			// метод: возвращает true,  
	{									// если переход на страницу товара правильный 
		List<WebElement> foundElements = null;
		foundElements = getList(list);				// получаем список элементов
		String str1 = getFirstText(foundElements);	// получаем текст первого элемента списка
		System.out.println("1-ый  в списке: " + str1);
		
		submitParam(marketSearch, str1);			// ввод данных в форму и ее отправка
		
		String str2 = getPageTitle();				// получаем page title
		
		if (str2.contains(str1) && str1 != "")		// проверка перехода на страницу товара
			return true;
		else 
			return false;
	}
	
}
