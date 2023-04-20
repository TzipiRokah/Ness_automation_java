import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayPOM extends BasePage{

    By searchBox;
    By searchBoxButton;
    By checkSearch;
    By chooseFilter;
    By checkFilter;
    By chooseItem;
    By addToCart;
    By addToCartMassage;
    By selectButton;
    By chooseSize;
    By itemAddToCart;

    //init all locators
    void initBy()
    {
        searchBox=By.cssSelector("#gh-ac");
        searchBoxButton=By.cssSelector("#gh-btn");
        chooseFilter=By.cssSelector("[class=\"srp-refine__category__item\"]");
        chooseItem=By.cssSelector("ul [class=\"s-item s-item__pl-on-bottom\"]");
        addToCart=By.cssSelector("[class=\"vim x-atc-action overlay-placeholder\"] a");
        checkSearch=By.cssSelector("[class=\"srp-controls__control srp-controls__count\"] h1");
        checkFilter=By.cssSelector("[class=\"srp-refine__category__item\"]");
        addToCartMassage=By.cssSelector("#x-msku__select-box-1000-error");
        selectButton=By.cssSelector("#x-msku__select-box-1000");
        chooseSize=By.cssSelector("#x-msku__option-box-0");
        itemAddToCart=By.cssSelector("#gh-cart-n");
    }

    public EbayPOM(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
        initBy();
    }

    //insert to page
    public void goToPage()
    {
        getDriver().get("https://www.ebay.com/");
    }

    //check search pitzer
    public boolean searchItem()
    {
        String searchWord="shoe";
        String checkResult;
        try {
            waitUntilVisbiltyElemntClickbel(searchBox);
        }catch (ElementClickInterceptedException ex){}
        typeInto(searchWord,searchBox);
        click(searchBoxButton);
        try {
            waitUntilVisbiltyElemntLocated(checkSearch);
        }catch (NoSuchElementException ex){}
        checkResult=getText(checkSearch).substring(23);
        System.out.println(checkResult);

        //check that expexted result is same to search
        if(searchWord.equals(checkResult))
            return true;
        return false;
    }

    //check filter pitzer
    public boolean chooseFilter()
    {
        String filterName;
        try{
            waitUntilVisbiltyElemntLocated(chooseFilter);
        }catch (ElementNotInteractableException ex){}
        filterName=getText(chooseFilter);
        click(chooseFilter);
        String resultFilter=getText(checkFilter);
        System.out.println(resultFilter + " "+filterName);
        //check that expexted result is same to filter choose
        if(filterName.equals(resultFilter))
            return true;
        return false;
    }

    //check add to card pitzer
    public boolean addToCart()
    {
        try {
            waitUntilVisbiltyElemntClickbel(chooseItem);
        }catch (ElementClickInterceptedException ex){}
        click(chooseItem);
        closeTab();
        try{
            waitUntilVisbiltyElemntLocated(selectButton);
        }catch (ElementClickInterceptedException ex){}

        //check that user can't bys item before choose size
        click(selectButton);
        click(chooseSize);
        waitUntilVisbiltyElemntClickbel(addToCart);
        click(addToCart);

        //check icon cart update to one item
        try{
            waitUntilVisbiltyElemntLocated(itemAddToCart);
            return true;
        }catch (NoSuchElementException ex){}
        return false;
    }
}
