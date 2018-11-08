package com.ititon.datacraw.controller;


//@RestController
public class PatentCrawController {

//    @Autowired
//    private ChromeDriverService chromeDriverService;
//
//    @Autowired
//    private PatentInitializer patentInitializer;
//
//    @Autowired
//    private PatentServiceImpl patentService;
//
//    private final String systemDownloadLocation;
//
//    {
//        systemDownloadLocation = FileUtils.getUserDirectory().getPath() + "/Downloads";
//    }
//
//    private ChromeDriver chromeDriver;
//    private WebDriverWait wait;
//    private Actions actions;
//
//
//    @GetMapping("/execute-chrome")
//    public ResponseEntity executeChrome() {
//
//        chromeDriver = new ChromeDriver(chromeDriverService);
//        wait = new WebDriverWait(chromeDriver, 60, 500);
//        actions = new Actions(chromeDriver);
//
////        chromeDriver.manage().window().fullscreen();
//        chromeDriver.get("http://pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml");
//
//        return ResponseEntity.ok().build();
//
//    }
//
//
//    @GetMapping("/open/patent/enter-login/{value}")
//    public ResponseEntity enterLoginValues(@PathVariable("value") String value) {
//
//        chromeDriver.findElement(By.id("j_username")).clear();
//        chromeDriver.findElement(By.id("j_password_show")).clear();
//        chromeDriver.findElement(By.id("j_validation_code")).clear();
////        chromeDriver.findElement(By.id("j_username")).sendKeys("amalka_123456789");
////        chromeDriver.findElement(By.id("j_password_show")).sendKeys("ABCDefg123456789");
//
//
//        chromeDriver.findElement(By.id("j_username")).sendKeys("olegsprin96");
//        chromeDriver.findElement(By.id("j_password_show")).sendKeys("Liberty700");
//        chromeDriver.findElement(By.id("j_validation_code")).sendKeys(value);
//
//        chromeDriver.findElement(By.id("wee_remember_me")).click();
////
//        chromeDriver.findElement(By.cssSelector("a[class='btn btn-login']")).click();
//
//
//        return ResponseEntity.ok().build();
//    }
//
//
//    private void fillSearchPanel() throws InterruptedException {
//
//
//        chromeDriver.get("http://pss-system.gov.cn/sipopublicsearch/patentsearch/tableSearch-showTableSearchIndex.shtml");
//
//        Actions actions = new Actions(chromeDriver);
//
//        Thread.sleep(3000);
//        chromeDriver.findElements(By.name("inventiontype")).forEach(element -> {
//            wait.until(ExpectedConditions.visibilityOf(element));
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//            if (!element.isSelected()) {
//                actions.click(element).build().perform();
//            }
//
//        });
//
//        chromeDriver.findElement(By.id("configTableItemBtnId")).click();
//
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='tableItemDiv']")));
//        List<WebElement> elements = chromeDriver.findElements(By.cssSelector("div[class='tableItemDiv']"));
//        System.out.println(elements.size());
//
//        for (WebElement element : elements) {
//
//            try {
//                element.findElement(By.cssSelector("input[value='IVDB103']"));
//                WebElement e1 = element.findElement(By.cssSelector("input[type='checkbox']"));
//                wait.until(ExpectedConditions.elementToBeClickable(e1));
//                if (!e1.isSelected()) {
//                    actions.click(e1).build().perform();
//                }
//            } catch (Throwable e) {
//
//            }
//
//            try {
//                element.findElement(By.cssSelector("input[value='IVDB022']"));
//                WebElement e2 = element.findElement(By.cssSelector("input[type='checkbox']"));
//                wait.until(ExpectedConditions.elementToBeClickable(e2));
//                if (!e2.isSelected()) {
//                    actions.click(e2).build().perform();
//                }
//            } catch (Throwable e) {
//
//            }
//
//        }
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-id='保存']")));
//
//        WebElement button = chromeDriver.findElement(By.cssSelector("button[data-id='保存']"));
//        actions.click(button).build().perform();
//
//        Thread.sleep(5000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tableSearchItemIdIVDB022")));
//        chromeDriver.findElement(By.id("tableSearchItemIdIVDB076")).sendKeys("CN");
//
//        chromeDriver.findElement(By.id("tableSearchItemIdIVDB075")).sendKeys("CN");
//
//        chromeDriver.findElement(By.id("tableSearchItemIdIVDB022")).sendKeys("贵州");
//
//        WebElement tableSearch = chromeDriver.findElement(By.cssSelector("a[onclick='excuteTableSearch();']"));
//        actions.click(tableSearch).build().perform();
//
////        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultMode")));
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
//
//    }
//
//
//    @GetMapping("/patent/search")
//    public ResponseEntity patentSearch() throws InterruptedException, IOException {
//
//        int page = 1;
//        savePatents(page);
//
//        return ResponseEntity.ok().build();
//
//    }
//
//    private void savePatents(int page) {
//        try {
//            fillSearchPanel();
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
//            if (page != 1) {
//                //пагинация
//                WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
//                //кнопки
//                List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));
//                Optional<WebElement> pageSearchButton = paginationButtons.stream()
//                        .filter(b -> !Objects.equals(null, b.getText()) && Objects.equals(b.getText(), "确定"))
//                        .findAny();
//
//                Actions actions = new Actions(chromeDriver);
//                WebElement inputPageElement = chromeDriver.findElement(By.id("txt"));
//                inputPageElement.clear();
//                inputPageElement.sendKeys(String.valueOf(page));
//                WebElement button = pageSearchButton.get();
//                actions.click(button).build().perform();
//
//            }
//            getPatents(page);
//        } catch (Throwable e) {
//            savePatents(page);
//        }
//    }
//
//    private void getPatents(int page) throws InterruptedException, IOException {
//        boolean isNotEnd = true;
//        while (isNotEnd) {
//            //пагинация
//            WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
//            //кнопки
//            List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));
//
//            //кнопка - next
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
//            Optional<WebElement> nextButton = paginationButtons.stream().filter(b -> !Objects.equals(null, b.getText())
//                    && Objects.equals(b.getText(), "下一页")).findAny();
//
//
//            Integer countOfPatentsOnPage = getCountOfPatentsOnPage();
//            Map<String, Patent> pagePatentsInfo = getAllPatentsFromPage();
//            for (int i = 0; i < countOfPatentsOnPage; i++) {
//                clickFullInfoButton(i);
//                waitVisibilityOfPatentFullInfo();
//
//                // доп инфа, тег чтобы получить самую первую строку с номером
//                WebElement tabContent_1_ul_id = chromeDriver.findElement(By.id("tabContent_1_ul_id"));
//                //это сам номер
//                String number = tabContent_1_ul_id.findElement(By.tagName("li")).findElement(By.tagName("a")).getText();
//
//
//                Map<SearchField, String> patentsInfoFromTable = getPatentInfoFromTable();
//
//                Patent patent = null;
//                String value = patentsInfoFromTable.get(SearchField.APPLICATION_NUM);
//                if (value != null) {
//                    patent = pagePatentsInfo.get(value);
//                }
//
//                if (patent != null) {
//                    initializePatent(patent, patentsInfoFromTable);
//                }
//            }
//
//            Collection<Patent> patents = pagePatentsInfo.values();
//            patentService.saveAll(patents);
//
//
//            if (nextButton.isPresent()) {
//                page++;
//                actions.click(nextButton.get()).build().perform();
//                System.out.println("PAGE IS " + page);
//                Thread.sleep(5000);
//            } else {
//                isNotEnd = false;
//            }
//
//        }
//
//    }
//
//    private Integer getCountOfPatentsOnPage() {
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
//        List<WebElement> elmts = chromeDriver.findElement(By.id("resultMode"))
//                .findElement(By.tagName("div"))
//                .findElement(By.cssSelector("div[class='list-container']"))
//                .findElement(By.tagName("ul"))
//                .findElements(By.tagName("li"));
//
//        return elmts.size();
//    }
//
//
//    private Map<String, Patent> getAllPatentsFromPage() {
//
//        List<WebElement> elementsByCssSelector =
//                chromeDriver.findElementsByCssSelector("div[class='item-content-body left']");
//
//        //информация по всем патентам которые находятся на странице
//        Map<String, Patent> pagePatentsInfo = new HashMap<>();
//        elementsByCssSelector.forEach(e -> {
//            Patent patent = new Patent();
//            StringBuilder applicationNum = new StringBuilder();
//            List<WebElement> ps = e.findElements(By.tagName("p"));
//            ps.forEach(p -> {
//                String text = p.getText();
//                String[] splittedText = text.split(" : ");
//                SearchField field = SearchField.findByValue(splittedText[0]);
//                if (Objects.equals(field, SearchField.APPLICATION_NUM1)) {
//                    applicationNum.append(splittedText[1]);
//                }
//                if (Objects.equals(field, SearchField.AGENT) || Objects.equals(field, SearchField.AGENCY)) {
//                    patentInitializer.initializePatentField(patent, splittedText[0], splittedText[1]);
//                }
//            });
//            pagePatentsInfo.put(applicationNum.toString(), patent);
//        });
//
//
//        return pagePatentsInfo;
//    }
//
//
//
//    private List<WebElement> getPatentFullInfoButtons() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='btn-group clear']")));
//        List<WebElement> buttons = chromeDriver.findElements(By.cssSelector("div[class='btn-group clear']"));
//
//        List<WebElement> patentsFullInfoButtons = new ArrayList<>();
//        buttons.forEach(e -> {
//            WebElement element = e.findElement(By.cssSelector("a[role='detail']"));
//            patentsFullInfoButtons.add(element);
//
//        });
//
//        return patentsFullInfoButtons;
//    }
//
//    private void clickFullInfoButton(int indexOfButton) {
//
//        List<WebElement> patentFullInfoButtons = getPatentFullInfoButtons();
//
//        WebElement descriptionOfPatentButton = patentFullInfoButtons.get(indexOfButton);
//        wait.until(ExpectedConditions.elementToBeClickable(descriptionOfPatentButton));
//        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
//        js.executeScript("detail_single(this);", descriptionOfPatentButton);
////        actions.click(descriptionOfPatentButton).build().perform();
//
//    }
//
//    private void waitVisibilityOfPatentFullInfo() {
//        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//        ArrayList<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());
//        chromeDriver.switchTo().window(tabs.get(1));
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabContent_1_id")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabContent_1_ul_id")));
//    }
//
//    private Map<SearchField, String> getPatentInfoFromTable() {
//        //тег в котором лежит таблица
//        WebElement tableContainer = chromeDriver.findElement(By.cssSelector("div[class='table-container']"));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
//        WebElement table = tableContainer.findElement(By.tagName("table"));
//
//        //теги из таблицы в котором лежать значения
//        List<WebElement> tableTags = table.findElements(By.tagName("tr"));
//
//        Map<SearchField, String> patentKeyValues = new LinkedHashMap<>();
//        for (WebElement t : tableTags) {
//            WebElement firstTd = t.findElement(By.cssSelector("td[class='first-td']"));
//            String key = firstTd.findElement(By.tagName("div")).getText();
//            String newKey = key;
//            //.replace("-", "");
//            WebElement secondTd = t.findElement(By.cssSelector("td[class='second-td']"));
//            String value = secondTd.findElement(By.tagName("div")).getText();
//            SearchField field = SearchField.findByValue(newKey);
//            patentKeyValues.put(field, value);
//        }
//        return patentKeyValues;
//    }
//
//    private void initializePatentFromTableValues(Map<SearchField, String> patentInfoFromTable, Patent patent) {
//        for (Map.Entry<SearchField, String> keyvalue : patentInfoFromTable.entrySet()) {
//            String translation = keyvalue.getKey().getValue();
//            String value1 = keyvalue.getValue();
//            patentInitializer.initializePatentField(patent, translation, value1);
//        }
//    }
//
//
//    private String getFullDescription() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fullTextTitleId")));
//        //елемент в котором лежит полное описание патента
//        WebElement fullTextTitleId = chromeDriver.findElement(By.id("fullTextTitleId"));
//        wait.until(ExpectedConditions.elementToBeClickable(fullTextTitleId));
//
//        actions.click(fullTextTitleId).build().perform();
//
//        //описание
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabContent_2_ul_id")));
//        WebElement tabContent_2_ul_id = chromeDriver.findElement(By.id("tabContent_2_ul_id"));
//
//        WebElement descriptionDiv = chromeDriver.findElement(By.cssSelector("div[class='fulltext-tab-content']"));
//        return descriptionDiv.getText();
//
//    }
//
//    private String getImageUrl() throws InterruptedException {
//        //элемент в котором содержится ссылка на картинку
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='figure_img']")));
//        WebElement imageElement = chromeDriver.findElement(By.cssSelector("img[class='figure_img']"));
//        //url для картинки
//        return imageElement.getAttribute("src");
//    }
//
//    private WebElement getLegalStatusElement() throws InterruptedException {
//        // юр статус
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lawStateContainerId")));
//        return chromeDriver.findElement(By.id("lawStateContainerId"))
//                .findElement(By.tagName("ul"))
//                .findElement(By.tagName("li"));
//    }
//
//    private String getLegalStatus() throws InterruptedException {
//        WebElement legalStatusElement = getLegalStatusElement();
//        return legalStatusElement.findElement(By.cssSelector("span[class='text']")).getText();
//    }
//
//    private LocalDate getLegalStausDate() throws InterruptedException {
//        WebElement legalStatusElement = getLegalStatusElement();
//        String legalDate = legalStatusElement.findElement(By.cssSelector("span[class='time']")).getText();
//
//        String year = legalDate.substring(0, 4);
//        String month = legalDate.substring(4, 6);
//        String day = legalDate.substring(6, 8);
//        return LocalDate.parse(year + "-" + month + "-" + day);
//    }
//
//
//
//
//    private void initializePatent(Patent patent, Map<SearchField, String> patentInfoFromTable) throws IOException, InterruptedException {
//        // тег чтоб получить title таблицы
//        WebElement tableContainerTitle = chromeDriver.findElement(By.cssSelector("div[class='table-container-title']"));
//        //сам title
//        String tableTitle = tableContainerTitle.getText();
//
//        initializePatentFromTableValues(patentInfoFromTable, patent);
//
//        String name = tableTitle.replace("发明名称", "").replace("-", "");
//        patentInitializer.initializePatentField(patent, SearchField.NAME.getValue(), tableTitle);
//
//        //элемент содержащий описание патента
//        WebElement patentDescription = chromeDriver.findElement(By.id("cpp_content_j20"));
//        //описание
//        String description = patentDescription.getText();
//        // источник
//        WebElement elementSourceOf = chromeDriver.findElement(By.id("patcitContainerId")).findElement(By.tagName("div"));
//        String sourceOf = elementSourceOf.getText();
//        String imageUrl = getImageUrl();
//        String fullDescription = getFullDescription();
//        patent.setDescription(description + "\n\n" + fullDescription);
//
//        String legalStatus = getLegalStatus();
//        LocalDate legalStatusDate = getLegalStausDate();
//
//        // тип
//        WebElement elemenPatentType = chromeDriver.findElement(By.id("cognationContainerId")).findElement(By.tagName("div"));
//        String patentType = elemenPatentType.getText();
//
//        ArrayList<String> windowHandles = new ArrayList<>(chromeDriver.getWindowHandles());
//        chromeDriver.get(imageUrl);
//
//        patentInitializer.initializePatentField(patent, SearchField.LEGAL_STATUS.getValue(), legalStatus);
//        patentInitializer.initializePatentField(patent, SearchField.PATENT_TYPE.getValue(), patentType);
//        patent.setLegalStatusAdded(legalStatusDate);
//        patent.setSourceOf(sourceOf);
//
//        String imageNewPath = moveImage(patent.getPublicationNum());
//        patent.setImagePath("/" + imageNewPath);
//
//        ((JavascriptExecutor) chromeDriver).executeScript("window.close()");
//        chromeDriver.switchTo().window(windowHandles.get(0));
//    }
//
//    private String moveImage(String publicationNum) throws InterruptedException, IOException {
//        Thread.sleep(5000);
//        File downloadsLocation = new File(systemDownloadLocation);
//        File[] downloads = downloadsLocation.listFiles();
//        String patentImageName = downloads[0].getName();
//        String[] split = patentImageName.split("[.]");
//
//        if (split.length > 2) {
//            Thread.sleep(5000);
//        }
//
//        String imageNewName = publicationNum + "." + split[1];
//        File correctImage = FileUtils.getFile("./src/main/resources/patent_images/" + imageNewName);
//        if (!correctImage.exists()) {
//            FileUtils.moveFile(FileUtils.getFile(systemDownloadLocation + "/" + split[0] + "." + split[1]),
//                    correctImage);
//        }
//
//        return imageNewName;
//    }
//
//
//    private ChromeDriver getChromeDriver() {
//        if (chromeDriver == null) {
//            chromeDriver = new ChromeDriver(chromeDriverService);
//        }
//
//        return chromeDriver;
//    }
//
//
//    @PreDestroy
//    public void destroy() {
//        chromeDriver.quit();
//    }
}
