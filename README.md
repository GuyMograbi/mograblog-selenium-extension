A thin wrapper around selenium to easily describe components and pages  

Read more about it at http://www.mograblog.com/2013/09/extending-selenium-even-more.html


# Installation 
 
This library uses jitpack as its repository. 

Follow instructions and see all available versions on (https://jitpack.io/#guymograbi/mograblog-selenium-extension)

# Usage

## Define your component which inherits from `MograblogElement`

```java
public class MograblogSelect extends MograblogElement {

    // rootElement and webDriver are available here
    
    // expose whatever interface you want
}
```

## Use your new component in a Page 

```java
public class MyPage {

    @FindBy(css="select#testme")
    public MograblogSelect select;

    public void changeToOption2(){
        select.val( "option2" );
    }

    public void changeToOption1(){
        select.val( "option1 ");

    }
}
```

## Initialize webdriver and use your new API

```java
WebDriver firefoxDriver = new FirefoxDriver();
firefoxDriver.get("some url");
MyPage page = new MyPage();

PageFactory.initElements( new MograblogFieldDecorator( firefoxDriver, firefoxDriver ), page );
page.changeToOption2(); // this should work now
```