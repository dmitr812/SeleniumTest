package web_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPage extends BasePage		// класс объектов web-страниц
{
	By priceFrom = By.id ("gf-pricefrom-var");				// локатор поля ввода цены "от" 
 	By priceTo = By.id ("gf-priceto-var");					// локатор поля ввода цены "до" 
 	By hpCheckbox = By.id ("gf-1801946-1870091");			// локатор чек-бокса HP
 	By lenovoCheckbox = By.id ("gf-1801946-1871127");		// локатор чек-бокса Lenovo
 	By acerCheckbox = By.id ("gf-1801946-3598551");			// локатор чек-бокса Acer
 	By dellCheckbox = By.id ("gf-1801946-1871523");			// локатор чек-бокса DELL
 	By ApplyBtn = By.xpath("//span[contains(text(),'Применить')]");	// локатор кнопки "Применить"
 	By MoreBtn = By.xpath("//span[contains(text(),'Ещё')]");// локатор кнопки "Ещё"
 	String price1 = "30000";								// параметр поиска (цена)
 	String price2 = "20000";								// параметр поиска (цена)
 	String price3 = "25000";								// параметр поиска (цена)
 	
	private WebDriver driver_;				// объект класса WebDriver
			
	public SearchPage(WebDriver driver) 	// конструктор:
	{										// передает driver в качестве параметра				
		super(driver);					// вызов конструктора суперкласса
		driver_ = super.driver; 		// обращаемся к driver из суперкласса
	}

	private void setCheckbox(By checkbox) 				// метод 
	{
		WebDriverWait wait = new WebDriverWait(driver_, 5);	// инициализация ожидания драйвера
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(checkbox)); // ожидание элемента
		//element.click();						// клик по найденному чек-боксу
		JavascriptExecutor executor = (JavascriptExecutor) driver_;
		executor.executeScript("arguments[0].click();", element);	
	}
		
	private void enterParam(By link, String str) 		// метод 
	{
		WebDriverWait wait = new WebDriverWait(driver_, 5);	// инициализация ожидания драйвера
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(link)); // ожидание элемента
		element.sendKeys(str);								// ввод строки в поле 
	}
	
	private void delay()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	public void Searching1()		// метод: поиск для TestCase #1
	{
		enterParam(priceTo, price1);	// ввод данных в поле параметра (цена до) 
		setCheckbox(hpCheckbox);		// клик по чек-боксу 
		setCheckbox(lenovoCheckbox);	// клик по чек-боксу
		clickLink(ApplyBtn);			// переход по клику по кнопке
		delay();		
	}
	
	public void Searching2()		// метод: поиск для TestCase #2
	{
		enterParam(priceFrom, price2);	// ввод данных в поле параметра (цена от) 
		enterParam(priceTo, price3);	// ввод данных в поле параметра (цена до)
		
		setCheckbox(acerCheckbox);		// клик по чек-боксу 
		if (!driver.findElement(dellCheckbox).isDisplayed())
		{
			clickLink(MoreBtn);			// переход по клику по ссылке
			System.out.println("click <Еще>");
		}
		setCheckbox(dellCheckbox);		// клик по чек-боксу
		clickLink(ApplyBtn);			// переход по клику по кнопке
		delay();
	}
}
