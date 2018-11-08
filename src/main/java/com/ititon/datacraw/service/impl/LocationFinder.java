package com.ititon.datacraw.service.impl;

import com.ititon.datacraw.model.location.Province;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
public class LocationFinder {

    private static final int PROVINCE_BEGIN_INDEX = 3;
    private static final int MAX_PROVINCE_LENGTH = 11;


    public static void main(String[] args) throws IOException, URISyntaxException {

        String l = "贵州省贵阳市乌当区高新路278号";

        System.out.println(l.substring(0, 4));
        System.out.println(l.substring(4, 6));
        System.out.println(l.substring(6, 8));
//        String path1 = LocationFinder.class.getResource("/p_scheme.png").getPath();
//        String path = FileUtils.getUserDirectory().getPath();
//
//        String newPathe = path + "/Downloads/";
//
//        File file = new File(newPathe);
//        File[] files = file.listFiles();
//        List<Long> collect =
//                Arrays.asList(files).stream().map(f -> f.lastModified())
//                        .collect(Collectors.toList());
//        long l = collect.stream().mapToLong(s -> s).max().orElseGet(() -> 0L);
//        File[] files = new File(newPathe).listFiles();
//        String name = files[0].getName();
//        System.out.println(name);
//        FileUtils.moveFile(FileUtils.getFile("C:/Users/van/Downloads/p_scheme.png"),
//                FileUtils.getFile("./src/main/resources/p_scheme.png"));

//        for (WebElement e : elementsByCssSelector) {
//            Patent patent = new Patent();
//            StringBuilder applicationNum = new StringBuilder();
//            List<WebElement> ps = e.findElements(By.tagName("p"));
//            for (WebElement p : ps) {
//                String text = p.getText();
//                String[] splittedText = text.split(" : ");
//                SearchField field = SearchField.findByValue(splittedText[0]);
//                if (Objects.equals(field, SearchField.APPLICATION_NUM)) {
//                    applicationNum.append(splittedText[1]);
//                }
//                if (Objects.equals(field, SearchField.AGENT) || Objects.equals(field, SearchField.AGENCY)) {
//                    patentInitializer.initializePatentField(patent, splittedText[0], splittedText[1]);
//                }
//            }
//
//            pagePatentsInfo.put(applicationNum.toString(), patent);
//
//        }
//        elementsByCssSelector.forEach(e -> {
//
//        });

    }

    public Province findProvince(final String line) {

        int endIndex = PROVINCE_BEGIN_INDEX + MAX_PROVINCE_LENGTH;
        StringBuilder searchLine = new StringBuilder();
        if (endIndex > line.length()) {
            searchLine.append(line);
        } else {
            searchLine.append(line.substring(PROVINCE_BEGIN_INDEX, endIndex));
        }
        return findProvince(searchLine, 3);
    }


    private Province findProvince(final StringBuilder searchLine, final Integer minLenght) {
        while (searchLine.length() >= minLenght) {
            Province province = Province.findProvince(searchLine.toString());
            if (province != null) {
                return province;
            }
            searchLine.setLength(searchLine.length() - 1);
        }

        return Province.UNKNOWN;
    }


    public String findRegion(final Province province, final String searchLine) {
        if (province == null) {
            throw new RuntimeException("Province not exist");
        }
        final Integer minRegionNameLength = province.getMinRegionNameLength();
        final Integer maxRegionNameLength = province.getMaxRegionNameLength();
        final String provinceCnName = province.getTranslation();
        int beginIndex = PROVINCE_BEGIN_INDEX + provinceCnName.length();
        int endIndex = beginIndex + maxRegionNameLength;
        String newCorrectedSearchLine;
        if (endIndex > searchLine.length()) {
            newCorrectedSearchLine = searchLine;
        } else {
            newCorrectedSearchLine = searchLine.substring(beginIndex, endIndex);
        }

        final StringBuilder line = new StringBuilder(newCorrectedSearchLine);
        String regionName = findRegionName(province, line, minRegionNameLength);
        if (Objects.equals("Unknown", regionName)) {
            return regionName;
        }
        return StringUtils.capitalize(regionName.toLowerCase());
    }




    private String findRegionName(final Province province, final StringBuilder searchLine, final Integer minLenght) {
        while (searchLine.length() >= minLenght) {
            String regionName = province.findRegionName(searchLine.toString());
            if (regionName != null) {
                return regionName;
            }
            searchLine.setLength(searchLine.length() - 1);
        }

        return "Unknown";
    }

    public String findAddress(final Province province, final String disctricName, final String searchLine) {
        final String provinceCnName = province.getTranslation();
        final int districtNameLength = disctricName.length();
        int provinceCnNameLength = provinceCnName.length();
        int length = PROVINCE_BEGIN_INDEX + provinceCnNameLength;
        if (length >= searchLine.length()) {
            return null;
        }
        return searchLine.substring(length);
    }



}
