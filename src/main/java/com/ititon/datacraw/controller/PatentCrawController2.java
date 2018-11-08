package com.ititon.datacraw.controller;

import com.ititon.datacraw.model.SearchField;
import com.ititon.datacraw.service.impl.PatenInitializer2;
import com.ititon.datacraw.service.impl.PatentServiceImpl;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PatentCrawController2 {

    @Autowired
    private ChromeDriverService chromeDriverService;

    @Autowired
    private PatenInitializer2 patentInitializer;

    @Autowired
    private PatentServiceImpl patentService;

    private final String systemDownloadLocation;

    {
        systemDownloadLocation = FileUtils.getUserDirectory().getPath() + "/Downloads";
    }

    private ChromeDriver chromeDriver;
    private WebDriverWait wait;
    private Actions actions;


    @GetMapping("/execute-chrome")
    public ResponseEntity executeChrome() {

        chromeDriver = new ChromeDriver(chromeDriverService);
        wait = new WebDriverWait(chromeDriver, 60, 7000);
        actions = new Actions(chromeDriver);

//        chromeDriver.manage().window().fullscreen();
        chromeDriver.get("http://pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml");

        return ResponseEntity.ok().build();

    }


    @GetMapping("/open/patent/enter-login/{value}")
    public ResponseEntity enterLoginValues(@PathVariable("value") String value) {

        chromeDriver.findElement(By.id("j_username")).clear();
        chromeDriver.findElement(By.id("j_password_show")).clear();
        chromeDriver.findElement(By.id("j_validation_code")).clear();
        chromeDriver.findElement(By.id("j_username")).sendKeys("Amal95");
        chromeDriver.findElement(By.id("j_password_show")).sendKeys("abcd123");

//        chromeDriver.findElement(By.id("j_username")).sendKeys("olegsprin96");
//        chromeDriver.findElement(By.id("j_password_show")).sendKeys("Liberty700");
        chromeDriver.findElement(By.id("j_validation_code")).sendKeys(value);

        chromeDriver.findElement(By.id("wee_remember_me")).click();
//
        chromeDriver.findElement(By.cssSelector("a[class='btn btn-login']")).click();

        return ResponseEntity.ok().build();
    }


    private void fillSearchPanel() throws InterruptedException {


        chromeDriver.get("http://pss-system.gov.cn/sipopublicsearch/patentsearch/tableSearch-showTableSearchIndex.shtml");

        Actions actions = new Actions(chromeDriver);

        Thread.sleep(3000);
        chromeDriver.findElements(By.name("inventiontype")).forEach(element -> {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (!element.isSelected()) {
                actions.click(element).build().perform();
            }

        });

        chromeDriver.findElement(By.id("configTableItemBtnId")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='tableItemDiv']")));
        List<WebElement> elements = chromeDriver.findElements(By.cssSelector("div[class='tableItemDiv']"));
        System.out.println(elements.size());

        for (WebElement element : elements) {

            try {
                element.findElement(By.cssSelector("input[value='IVDB103']"));
                WebElement e1 = element.findElement(By.cssSelector("input[type='checkbox']"));
                wait.until(ExpectedConditions.elementToBeClickable(e1));
                if (!e1.isSelected()) {
                    actions.click(e1).build().perform();
                }
            } catch (Throwable e) {

            }

            try {
                element.findElement(By.cssSelector("input[value='IVDB022']"));
                WebElement e2 = element.findElement(By.cssSelector("input[type='checkbox']"));
                wait.until(ExpectedConditions.elementToBeClickable(e2));
                if (!e2.isSelected()) {
                    actions.click(e2).build().perform();
                }
            } catch (Throwable e) {

            }

        }

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-id='保存']")));

        WebElement button = chromeDriver.findElement(By.cssSelector("button[data-id='保存']"));
        actions.click(button).build().perform();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tableSearchItemIdIVDB022")));
        chromeDriver.findElement(By.id("tableSearchItemIdIVDB076")).sendKeys("CN");

        chromeDriver.findElement(By.id("tableSearchItemIdIVDB075")).sendKeys("CN");

        chromeDriver.findElement(By.id("tableSearchItemIdIVDB022")).sendKeys("贵州");

        WebElement tableSearch = chromeDriver.findElement(By.cssSelector("a[onclick='excuteTableSearch();']"));
        actions.click(tableSearch).build().perform();

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultMode")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));

    }


    @GetMapping("/patent/search")
    public ResponseEntity patentSearch() throws InterruptedException, IOException {

        AtomicInteger page = new AtomicInteger(1);
        AtomicInteger patentCounter = new AtomicInteger(0);
        fillSearchPanel();
        savePatents(page, 0, patentCounter);

        return ResponseEntity.ok().build();

    }

    private void savePatents(AtomicInteger page, Integer errCounter, AtomicInteger countOFElement) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
            if (page.get() != 1) {
                Actions actions = new Actions(chromeDriver);
                //пагинация
                WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
                //кнопки
                List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));
                Optional<WebElement> pageSearchButton = paginationButtons.stream()
                        .filter(b -> !Objects.equals(null, b.getText()) && Objects.equals(b.getText(), "确定"))
                        .findAny();


                WebElement inputPageElement = chromeDriver.findElement(By.id("txt"));
                inputPageElement.clear();

//                int i = new Random().nextInt(11189 - 1) + 1;
                inputPageElement.sendKeys(String.valueOf(page.get()));
                WebElement button = pageSearchButton.get();
                wait.until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                actions.click(button).build().perform();
                Thread.sleep(10000);
            }
            savePatentsFromPage(page, countOFElement);
        } catch (Throwable e) {
            if (errCounter > 10) {
                return;
            }
            e.printStackTrace();
            System.out.println("***********ERROR****************");
            System.out.println("**************PAGE IS : " + (page.get() - errCounter) + "*************");
            page.getAndDecrement();
            page.set(page.get());
            savePatents(page, errCounter, countOFElement);
        }
    }

    private Optional<WebElement> getNextButton() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
        //пагинация
        WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
        //кнопки
        List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));

        return paginationButtons.stream().filter(b -> !Objects.equals(null, b.getText())
                && Objects.equals(b.getText(), "下一页")).findAny();
    }

    private Integer getCountOfPatentsOnPage() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
        List<WebElement> elmts = chromeDriver.findElement(By.id("resultMode"))
                .findElement(By.tagName("div"))
                .findElement(By.cssSelector("div[class='list-container']"))
                .findElement(By.tagName("ul"))
                .findElements(By.tagName("li"));

        return elmts.size();
    }

    private List<WebElement> getPatentFullInfoButtons() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='btn-group clear']")));
        List<WebElement> buttons = chromeDriver.findElements(By.cssSelector("div[class='btn-group clear']"));

        List<WebElement> patentsFullInfoButtons = new ArrayList<>();
        buttons.forEach(e -> {
            WebElement element = e.findElement(By.cssSelector("a[role='detail']"));
            patentsFullInfoButtons.add(element);

        });

        return patentsFullInfoButtons;
    }

    private void clickFullInfoButton(int indexOfButton) {

        List<WebElement> patentFullInfoButtons = getPatentFullInfoButtons();

        List<WebElement> elements = chromeDriver.findElement(By.cssSelector("div[class='list-container']")).findElement(By.tagName("ul"))
                .findElements(By.tagName("li"));
        WebElement descriptionOfPatentButton = patentFullInfoButtons.get(indexOfButton);
        wait.until(ExpectedConditions.elementToBeClickable(descriptionOfPatentButton));
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("arguments[0].scrollIntoView();", elements.get(indexOfButton));
//        js.executeScript("detail_single(this);", descriptionOfPatentButton);
        actions.click(descriptionOfPatentButton).build().perform();

    }

    private void waitVisibilityOfPatentFullInfo() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabContent_1_id")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabContent_1_ul_id")));
    }


    private Map<SearchField, String> getPatentInfoFromTable() {
        //тег в котором лежит таблица
        WebElement tableContainer = chromeDriver.findElement(By.cssSelector("div[class='table-container']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        WebElement table = tableContainer.findElement(By.tagName("table"));

        //теги из таблицы в котором лежать значения
        List<WebElement> tableTags = table.findElements(By.tagName("tr"));

        Map<SearchField, String> patentKeyValues = new LinkedHashMap<>();
        for (WebElement t : tableTags) {
            WebElement firstTd = t.findElement(By.cssSelector("td[class='first-td']"));
            String key = firstTd.findElement(By.tagName("div")).getText();
            String newKey = key;
            WebElement secondTd = t.findElement(By.cssSelector("td[class='second-td']"));
            String value = secondTd.findElement(By.tagName("div")).getText();
            SearchField field = SearchField.findByValue(newKey);
            patentKeyValues.put(field, value);
        }
        return patentKeyValues;
    }

    private void savePatentsFromPage(AtomicInteger page, AtomicInteger countOfElement) throws InterruptedException, IOException {
        boolean isNotEnd = true;
        while (isNotEnd) {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));


            Optional<WebElement> nextButton = getNextButton();

            Integer count = getCountOfPatentsOnPage();

            for (int i = countOfElement.get(); i < count; i++) {
                System.out.println("**************WORKING WITH ELEMENT: " + i + "*************");

                clickFullInfoButton(i);
                waitVisibilityOfPatentFullInfo();

                // доп инфа, тег чтобы получить самую первую строку с номером
                WebElement tabContent_1_ul_id = chromeDriver.findElement(By.id("tabContent_1_ul_id"));
                //это сам номер
                String number = tabContent_1_ul_id.findElement(By.tagName("li")).findElement(By.tagName("a")).getText();


                Map<SearchField, String> patentsInfoFromTable = getPatentInfoFromTable();

                String value = patentsInfoFromTable.get(SearchField.APPLICATION_NUM);

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("patcitContainerId")));
                WebElement elementSourceOf = chromeDriver.findElement(By.id("patcitContainerId")).findElement(By.tagName("div"));

                String sourceOf = elementSourceOf.getText();

                List<String> legalStatuses = getLegalStatus();
                List<LocalDate> legalStausDates = getLegalStausDate();

                String imageUrl = getImageUrl();

                //элемент содержащий описание патента
                WebElement patentDescription = chromeDriver.findElement(By.id("cpp_content_i0j3"));
                //описание
                String description = patentDescription.getText();


                List<String> collect =
                        patentsInfoFromTable.entrySet().stream().map(k -> k.getKey() + " : " + k.getValue()).collect(Collectors.toList());
                collect.add("SOURCE OF : " + sourceOf);
                collect.add("DESCRIPTION: " + description);


                for (int j = 0; j < legalStatuses.size(); j++) {
                    String status = legalStatuses.get(j);
                    LocalDate date = legalStausDates.get(j);

                    collect.add("LEGAL_STATUS : " + status);
                    collect.add("LEGAL_DATE : " + date);
                }

                String fileName = "./src/main/resources/db.txt";

                if (!imageUrl.contains("noimg.png")) {
                    chromeDriver.get(imageUrl);
                    String imageName = moveImage(value);
                    collect.add("IMAGE_NAME : " + imageName);
                }

                String fullDescription = getFullDescription();
                collect.add("FULL_DESCRIPTION : " + fullDescription);

                String join = String.join("\n", collect) + "\n" + " DELIMETER " + "\n";

                Files.write(
                        Paths.get(fileName),
                        join.getBytes(),
                        StandardOpenOption.APPEND);

                ArrayList<String> windowHandles = new ArrayList<>(chromeDriver.getWindowHandles());
                ((JavascriptExecutor) chromeDriver).executeScript("window.close()");
                chromeDriver.switchTo().window(windowHandles.get(0));
                countOfElement.getAndIncrement();

            }


            countOfElement.set(0);
            if (nextButton.isPresent()) {
                WebElement button = nextButton.get();
                wait.until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                actions.click(button).build().perform();

                page.getAndIncrement();

                Thread.sleep(10000);
            } else {
                isNotEnd = false;
            }
        }
    }

    private String getFullDescription() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fullTextTitleId")));
        //елемент в котором лежит полное описание патента
        WebElement fullTextTitleId = chromeDriver.findElement(By.id("fullTextTitleId"));
        wait.until(ExpectedConditions.elementToBeClickable(fullTextTitleId));

        actions.click(fullTextTitleId).build().perform();

        //описание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabContent_2_ul_id")));
        WebElement tabContent_2_ul_id = chromeDriver.findElement(By.id("tabContent_2_ul_id"));

        WebElement descriptionDiv = chromeDriver.findElement(By.cssSelector("div[class='fulltext-tab-content']"));
        return descriptionDiv.getText();

    }

    private String getImageUrl() throws InterruptedException {
        //элемент в котором содержится ссылка на картинку
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='figure_img']")));
        WebElement imageElement = chromeDriver.findElement(By.cssSelector("img[class='figure_img']"));
        //url для картинки
        return imageElement.getAttribute("src");
    }

    private List<WebElement> getLegalStatusElement() throws InterruptedException {
        // юр статус
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lawStateContainerId")));
        return chromeDriver.findElement(By.id("lawStateContainerId"))
                .findElement(By.tagName("ul"))
                .findElements(By.tagName("li"));
    }

    private List<String> getLegalStatus() throws InterruptedException {
        List<WebElement> legalStatusElement = getLegalStatusElement();
        ArrayList<String> results = new ArrayList<>();
        legalStatusElement.forEach(e -> results.add(e.findElement(By.cssSelector("span[class='text']")).getText()));
        return results;
    }

    private List<LocalDate> getLegalStausDate() throws InterruptedException {
        List<WebElement> legalStatusElement = getLegalStatusElement();
        ArrayList<LocalDate> results = new ArrayList<>();

        legalStatusElement.forEach(e -> {
            String legalDate = e.findElement(By.cssSelector("span[class='time']")).getText();
            String year = legalDate.substring(0, 4);
            String month = legalDate.substring(4, 6);
            String day = legalDate.substring(6, 8);
            LocalDate parse = LocalDate.parse(year + "-" + month + "-" + day);
            results.add(parse);

        });


        return results;
    }


    private String moveImage(String publicationNum) throws InterruptedException, IOException {
        Thread.sleep(3000);
        File downloadsLocation = new File(systemDownloadLocation);
        File[] downloads = downloadsLocation.listFiles();
        String patentImageName = downloads[0].getName();
        String[] split = patentImageName.split("[.]");

        if (split.length > 2) {
            Thread.sleep(5000);
        }


        String imageNewName = publicationNum + "." + split[1];
        File correctImage = FileUtils.getFile("./src/main/resources/patent_images/" + imageNewName);
        if (!correctImage.exists()) {
            File file = FileUtils.getFile(systemDownloadLocation + "/" + split[0] + "." + split[1]);
            FileUtils.moveFile(file,
                    correctImage);
        }

        return imageNewName;
    }


    @GetMapping("/start")
    public ResponseEntity loadFromFile() throws IOException {

//        patentService.smth();

//        File file1 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/1.txt");
//        aaa(file1);
//
//        File file2 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/2.txt");
//        aaa(file2);
//
//        File file3 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/3.txt");
//        aaa(file3);
//
//        File file4 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/4.txt");
//        aaa(file4);

        //CN201310456420
//        File file5 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/5-1.txt");
//        aaa(file5);
//
//        File file6 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/6.txt");
//        aaa(file6);

//        File file7 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/7.txt");
//        aaa(file7);

//        File file8 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/8.txt");
//        aaa(file8);
//
//        File file9 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/9.txt");
//        aaa(file9);
//
//        File file11 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/11.txt");
//        aaa(file11);

//        File file10 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/10.txt");
//        aaa(file10);

        File file12 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/12.txt");
        aaa(file12);
        File file13 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/13.txt");
        aaa(file13);
        File file14 = FileUtils.getFile("/home/amal/IdeaProjects/datacraw/src/main/resources/db/14.txt");
        aaa(file14);


        return ResponseEntity.ok().build();
    }

    public void aaa(File file) throws IOException {

        //CN201610956446   number 10
        Map<String, String> pvalues = new LinkedHashMap<>();

        try (Stream<String> lines = Files.lines(Paths.get(file.toURI()))) {
            lines.forEach(line -> {
                String trimedLine = line.trim();
                if (!trimedLine.equalsIgnoreCase("DELIMETER")) {
                    String[] splittedLine = line.split(":");
                    if (splittedLine.length >= 2) {
                        pvalues.put(splittedLine[0].trim(), splittedLine[1].trim());
                    }
                } else {
                    patentInitializer.initAndSave(pvalues);
                    pvalues.clear();
                }
            });
        }
    }


    @PreDestroy
    public void destroy() {
        chromeDriver.quit();
    }


    private void downloadHidden() {
        List<WebElement> hiddenDivsWithValues = chromeDriver.findElement(By.id("resultMode"))
                .findElements(By.cssSelector("div[class='item']"));

        for (WebElement element : hiddenDivsWithValues) {
            Map<String, String> hiddenNamesValues = new LinkedHashMap<>();
            List<WebElement> divInputs = element.findElements(By.tagName("input"));
            for (WebElement input : divInputs) {
                String name = input.getAttribute("name");
                String value = input.getAttribute("value");
                hiddenNamesValues.put(name, value);
            }

            List<WebElement> tableElements =
                    element.findElement(By.cssSelector("div[class='item-content-body left']"))
                            .findElements(By.tagName("p"));

            for (WebElement tableElement : tableElements) {
                String text = tableElement.getText();
                String[] keysValues = text.split(":");
                String key = keysValues[0].trim();
                SearchField field = SearchField.findByValue(key);
                String value = null;

//                    System.out.println("TEXT " + text);
                if (keysValues.length > 1) {
                    value = keysValues[1].trim();
                }
                if (Objects.equals(field, SearchField.AGENT)) {
                    hiddenNamesValues.put("agent", value);
                } else if (Objects.equals(field, SearchField.AGENCY)) {
                    hiddenNamesValues.put("agency", value);
                } else if (Objects.equals(field, SearchField.LA_NUM)) {
                    hiddenNamesValues.put("lanum", value);
                } else if (Objects.equals(field, SearchField.IPC_NUM)) {
                    System.out.println("IPC");
                    hiddenNamesValues.put("ipc", value);
                }
            }

//                List<String> collect =
//                        hiddenNamesValues.entrySet().stream().map(k -> k.getKey() + " : " + k.getValue()).collect(Collectors.toList());
//                String join = String.join("\n", collect) + "\n" + " DELIMETER " + "\n";
//
//                String fileName = "./src/main/resources/db.txt";
//
//                Files.write(
//                        Paths.get(fileName),
//                        join.getBytes(),
//                        StandardOpenOption.APPEND);

            patentInitializer.initAndSave(hiddenNamesValues);
        }
    }
}
