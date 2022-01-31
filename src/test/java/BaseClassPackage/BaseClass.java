package BaseClassPackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static void browserlaunchinjava(String drivername) {
		if (drivername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\RAJ\\Downloads\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();
		} else if (drivername.equals("edge")) {

			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\RAJ\\Downloads\\edgedriver_win64\\msedgedriver.exe");

			driver = new EdgeDriver();

		} else if (drivername.equals("gecko")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\RAJ\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");

			driver = new FirefoxDriver();

		} else {
			System.out.println("invalid browser name");
		}
	}
	
	
	
	public static void toFillTextBoxByWebWait(String locname,String loc,String value) {
	
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(100));
		if(locname.equals("id")) {
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc))).sendKeys(value);
		}else if(locname.equals("name")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc))).sendKeys(value);

		}else if(locname.equals("xpath")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).sendKeys(value);

		}else {
			System.out.println("Invalid Locator Name");
		}
	}
	
	public static void toClickByWebWait(String locname,String loc) {
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(100));
		if(locname.equals("id")) {
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc))).click();
		}else if(locname.equals("name")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc))).click();

		}else if(locname.equals("xpath")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).click();

		}else {
			System.out.println("Invalid Locator Name");
		}
	}
	
	
	public static void fluentWait() {
		FluentWait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
	}
	
	public static  void impwait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

	}
	
public static void pageLoadWait() {
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
	}

	public static void toFillTextBoxByFluWait(String locname,String loc,String value) {
		FluentWait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
		if(locname.equals("id")) {
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc))).sendKeys(value);
		}else if(locname.equals("name")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc))).sendKeys(value);

		}else if(locname.equals("xpath")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).sendKeys(value);

		}else {
			System.out.println("Invalid Locator Name");
		}
	}
	
	public static void toClickByFluWait(String locname,String loc) {
		FluentWait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
		if(locname.equals("id")) {
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc))).click();
		}else if(locname.equals("name")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc))).click();

		}else if(locname.equals("xpath")) {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).click();

		}else {
			System.out.println("Invalid Locator Name");
		}
	}
	
	
	
	
	
	
	

	public static Object[][] datas(String fileloc,String sheetName) throws IOException {
		File file = new File(fileloc);
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(0);
		Cell cell2 = row.getCell(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		int cellcount = row.getPhysicalNumberOfCells();

		Object[][] obj = new Object[rowcount - 1][cellcount];

		for (int i = 0; i < rowcount - 1; i++) {
			Row row2 = sheet.getRow(i + 1);
			for (int j = 0; j < row2.getPhysicalNumberOfCells(); j++) {
				
					Cell cell = row2.getCell(j);
					int cellType = cell.getCellType();
					String value;
					if (cellType == 1) {
						value = cell.getStringCellValue();
					} else {
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							SimpleDateFormat simpleDteFrmt = new SimpleDateFormat("dd-MM-yyyy");
							value = simpleDteFrmt.format(date);
						} else {
							double numericCellValue = cell.getNumericCellValue();
							long lng = (long) numericCellValue;
							value = String.valueOf(lng);
						}
					}
						obj[i][j] = value;				
			}
		}
		return obj;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void browserlaunchinmaven(String drivername) {
		if (drivername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--disable-notifications");
			driver = new ChromeDriver(co);
		} else if (drivername.equals("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new ChromeDriver();

		} else if (drivername.equals("gecko")) {
			WebDriverManager.firefoxdriver().setup();
			
			driver = new ChromeDriver();

		} else {
			sysout("invalid browser");// inga paaru raj sysout da namma base class la create panna method namma nala
										// base class
										// kullaye call panni kira mudiyuthu paru raj.....
		}
	}

	public static void urlLaunch(String urllaunch) {

		driver.get(urllaunch);
	}

	public static void datePicker(String moth,String yer,String day) {
		FluentWait<WebDriver> f = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);

		String Month = f.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_label xdsoft_month'])[1]"))).getText().trim();
		String Year = f.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_label xdsoft_year'])[1]"))).getText().trim();
		if(!(Month.equals(moth)) && !(Year.equals(yer))) {
			f.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='xdsoft_next'])[1]"))).click();
			Month = f.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_label xdsoft_month'])[1]"))).getText().trim();
			 Year = f.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_label xdsoft_year'])[1]"))).getText().trim();
		while (!(Month.equals(moth)) && (Year.equals(yer))) {
							f.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='xdsoft_next'])[1]"))).click();
		 Month = f.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_label xdsoft_month'])[1]"))).getText().trim();
		System.out.println(Month);
		 Year = f.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_label xdsoft_year'])[1]"))).getText().trim();
	}
		}
	f.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='"+day+"'])[1]"))).click();

	}
	
	
	
	public Object[][] test1() throws IOException {
		File file = new File("C:\\Users\\rajkumar.c\\eclipse-workspace\\Connect\\Excel\\LUTON.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(0);
		Cell cell2 = row.getCell(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		int cellcount = row.getPhysicalNumberOfCells();

		Object[][] obj = new Object[rowcount - 1][cellcount];

		for (int i = 0; i < rowcount - 1; i++) {
			Row row2 = sheet.getRow(i + 1);
			for (int j = 0; j < row2.getPhysicalNumberOfCells(); j++) {
				
					Cell cell = row2.getCell(j);
					int cellType = cell.getCellType();
					String value=null;
					if (cellType == 1) {
						value = cell.getStringCellValue();
					} else {
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							SimpleDateFormat simpleDteFrmt = new SimpleDateFormat("dd-MM-yyyy");
							value = simpleDteFrmt.format(date);
						} else {
							double numericCellValue = cell.getNumericCellValue();
							long lng = (long) numericCellValue;
							value = String.valueOf(lng);
						}
					}
					if (value.equals(value)) {
						obj[i][j] = value;

					}
				
			}
		
		}
		return obj;
		
		

	}


	
	
	
	
	
	
	
	
	
	public static void implicitywait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static void sendkeys(WebElement webElementReferenceName, String anynameyouneed) {

		webElementReferenceName.sendKeys(anynameyouneed);

	}

	public static void click(WebElement webElementReferenceName) {
		webElementReferenceName.click();
	}

	public static void close() {
		driver.close();
	}

	public static void quit() {
		driver.quit();
	}

	public static void sysout(String printsomethingifyouneed) {
		System.out.println(printsomethingifyouneed);
	}

	public static void syserr(String printsomethingifyouneed) {
		System.err.println(printsomethingifyouneed);
	}

	public static String gettitle() {
		String title = driver.getTitle();
		return title;

		// return null; // ipadiyum kodukkalam raj
	}

	public static String getcurrenturl() {

		String currentUrl = driver.getCurrentUrl();
		return currentUrl;

		// return driver.getCurrentUrl(); //ippadi ore thavum kodukalam raj
		// return getcurrenturl(); ippadiyum kodukkalam raj
	}

	public static String getText(WebElement webelementrefname) {

		String text = webelementrefname.getText();
		return text;
	}

	private String getAttribute(WebElement webelementrefname, String value) {
		String attribute = webelementrefname.getAttribute(value);
		return attribute;

	}

	private void clear(WebElement webelementrefname) {
		webelementrefname.clear();
	}

	private boolean isEnabled(WebElement webelementrefname) {
		return webelementrefname.isEnabled();

	}

	private boolean isSelected(WebElement webelementrefname) {
		return webelementrefname.isSelected();

	}

	private Point getLocation(WebElement webelementrefname) {
		Point location = webelementrefname.getLocation();
		return location;

	}

	private int getX(Point webelementrefname) {
		double x = webelementrefname.getX();
		return 0;

	}

	private int getY(Point webelementrefname) {
		double y = webelementrefname.getY();
		return 0;
	}

	private String getCssValue(WebElement webelementrefname, String enterCssValueLikeBackgroundColor) {
		String cssValue = webelementrefname.getCssValue(enterCssValueLikeBackgroundColor);
		return cssValue;

		// return webelementrefname.getCssValue(enterCssValueLikeBackgroundColor);
	}

	public int getSizeAndHeight(WebElement webelementrefname) {
		int height = webelementrefname.getSize().getHeight();
		return height;
	}

	public int getSizeAndWidth(WebElement webelementrefname) {
		int width = webelementrefname.getSize().getWidth();
		return width;
	}

	public static void selectByIndex(WebElement webelementrefname, int index) {
		Select s = new Select(webelementrefname);
		s.selectByIndex(index);
	}

	public static void selectByValue(WebElement webelementrefname, String index) {
		Select s = new Select(webelementrefname);
		s.selectByValue(index);
	}

	public static void selectByVisibleText(WebElement webelementrefname, String index) {

		Select s = new Select(webelementrefname);
		s.selectByVisibleText(index);
	}

	public List<WebElement> getOptions(WebElement webelementrefname) {
		Select s = new Select(webelementrefname);
		List<WebElement> list = s.getOptions();
		return list;
	}

	public int getsize(List<WebElement> webelementrefname) {
		int size = webelementrefname.size();
		return size;
	}

	public void setAttributete(String fillSomething, WebElement webelementrefname) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + fillSomething + "'", webelementrefname);
	}

	public Object getAttribute(String fillSomething, WebElement webelementrefname) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].getAttribute('value','" + fillSomething + "'",
				webelementrefname);

		return executeScript;
	}

	public void scrollDown(WebElement webelementrefname) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].scrollIntoView(true)", webelementrefname);
	}

	public void scrollUp(WebElement webelementrefname, String fillSomething) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].scrollIntoView(false)", webelementrefname);

	}

	public void javaScriptButtonClick(WebElement webelementrefname) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].Click()", webelementrefname);
	}

	public void screenshot(String Name) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File srcFile = tk.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\MavenProjectFirstClass1\\target" + Name + ".png");

		FileUtils.copyFile(srcFile, destFile);

	}

	public void doubleClick(WebElement target) {
		Actions a = new Actions(driver);
		a.doubleClick(target).build().perform();
	}

	public void contextClick(WebElement target) {
		Actions a = new Actions(driver);
		a.contextClick(target).build().perform();
	}

	public void dragAndDrop(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).build().perform();
	}

	public void Click(WebElement target) {
		Actions a = new Actions(driver);
		a.click(target).build().perform();
	}

	public void clickAndHold(WebElement target) {
		Actions a = new Actions(driver);
		a.clickAndHold(target).build().perform();
	}

	public void moveToElement(WebElement target) {
		Actions a = new Actions(driver);
		a.moveToElement(target).build().perform();
	}

	public void oneStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 1; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public void twoStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 2; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	public void threeStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 3; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	public void fourStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 4; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	public void fiveStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 5; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	public void sixStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 6; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	private void sevenStep() throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < 7; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	private void controlC() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);

	}

	private void controlV() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);

	}

	public static WebElement findElement(String locatorName, String passId) {
		WebElement findElement = null;
		// WebElement findElement = null; ippadiyum podalam raj......
		if (locatorName.equals("id")) {
			findElement = driver.findElement(By.id(passId));
		} else if (locatorName.equals("name")) {
			findElement = driver.findElement(By.id(passId));
		} else if (locatorName.equals("xpath")) {
			findElement = driver.findElement(By.id(passId));
		}
		return findElement;
	}

	public static String windowhandle() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}

	public static void windowhandlingByList(int index) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> li = new ArrayList<String>();
		li.addAll(windowHandles);
		driver.switchTo().window(li.get(index));

	}

	public static void switchAlert() {
		driver.switchTo().alert();
	}

	public static void switchframe(String index) {
		driver.switchTo().frame(index);
	}

	public static WebElement switchframebylocators(String locatorname, String text) {
		WebElement findElement = null;
		if (locatorname.equals("id")) {
			findElement = driver.findElement(By.id(text));
		} else if (locatorname.equals("name")) {
			findElement = driver.findElement(By.name(text));
		} else if (locatorname.equals("xpath")) {
			findElement = driver.findElement(By.xpath(text));
		} else {
			sysout("invalid locators");
		}
		return findElement;
	}

	
	
	
	public static String excelRead(String filename, String sheetname, int rownum, int cellnum) throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				"C:\\Users\\rajkumar.c\\eclipse-workspace\\Connect\\Excel\\" + filename
						+ ".xlsx");
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();
		String value = null;
		if (cellType == 1) {
			value = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			value = new SimpleDateFormat().format(cell.getDateCellValue());
		} else {
			value = String.valueOf((long) cell.getNumericCellValue());
		}
		return value;
	}

	public static void excelWriteByCreate(String filename, String sheetname, int rownum, int cellnum, String setValue)
			throws IOException {

		File file = new File(
				"C:\\Users\\RAJ\\eclipse-workspace\\MavenProjectForFrameExcel\\src\\test\\resources\\Excel\\" + filename
						+ ".xlsx");
		Workbook workbook = new XSSFWorkbook();
		Sheet createSheet = workbook.createSheet(sheetname);
		Row createRow = createSheet.createRow(rownum);
		Cell createCell = createRow.createCell(cellnum);
		createCell.setCellValue(setValue);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}

	public static void excelWriteInExisting(String filename, String sheetname, int rownum, int cellnum, String setValue)
			throws IOException {

		File file = new File(
				"C:\\Users\\RAJ\\eclipse-workspace\\MavenProjectForFrameExcel\\src\\test\\resources\\Excel\\" + filename
						+ ".xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet createSheet = workbook.getSheet(sheetname);
		Row createRow = createSheet.getRow(rownum);
		Cell createCell = createRow.createCell(cellnum);
		createCell.setCellValue(setValue);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}

	public static void excelUpdate(String filename, String sheetname, int rownum, int cellnum, String checkpanravalue,
			String setpanravalue) throws IOException {
		File file = new File(
				"C:\\Users\\RAJ\\eclipse-workspace\\MavenProjectForFrameExcel\\src\\test\\resources\\Excel\\" + filename
						+ ".xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet Sheet = workbook.getSheet(sheetname);
		Row row = Sheet.getRow(rownum);
		Cell Cell = row.getCell(cellnum);
		String Value = Cell.getStringCellValue();
		if (Value.equals(checkpanravalue)) {
			Cell.setCellValue(setpanravalue);
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}

	
	
	
	
	public static void pageLoadWait(int num) {
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(num));
	}
	
}
