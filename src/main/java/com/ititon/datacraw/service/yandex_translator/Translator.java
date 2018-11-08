package com.ititon.datacraw.service.yandex_translator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ititon.datacraw.service.impl.LocationFinder;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {

    public static final String FOR_TRANSLATE = "text=";
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String LANGUAGE = "&lang=";
    private static int i = 0;

    private ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Translator translator = new Translator();
        LocationFinder locationFinder = new LocationFinder();

//        String region = finder.findRegion();
//        System.out.println(region);
//        String cnAddress = "贵州省黔东南苗族侗族自治州凯里市经济开发区开元大道3号";
//        String cnText = "贵州省遵义市仁怀市茅台镇椿树村同心组30号";
//        String translatedText = translator.translate("zh-en", cnAddress);
//        System.out.println(translatedText);

//        if (!qian.equals(stringBuilder.toString())) {
//            System.out.println("not equal");
//            stringBuilder.setLength(stringBuilder.length() - 1);
//
//            System.out.println(qian.equals(stringBuilder.toString()));
//        };

//        System.out.println(translator.translate("zh-en", "遵义市"));
//        System.out.println(translator.translate("zh-en", "安顺市"));
//        System.out.println(translator.translate("zh-en", "毕节市"));
//        System.out.println(translator.translate("zh-en", "铜仁市"));
//        System.out.println(translator.translate("zh-en", "六盘水市"));
//        System.out.println(translator.translate("zh-en", "申请号"));
//        System.out.println(translator.translate("zh-en", "申请日"));
//        System.out.println(translator.translate("zh-en", "公开（公告）号"));


//        System.out.println(translator.translate("en", "贵阳市"));
    }

    private String translate(final String lang, final String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20180901T140354Z.5d6fff7d00efe488.fe0de9c3d83f104c4098848fcd3a7e140b0bcb8e";
        URL url = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes(FOR_TRANSLATE + URLEncoder.encode(input, ENCODING_UTF_8) + LANGUAGE + lang);

        InputStream response = connection.getInputStream();

        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode textNode = jsonNode.findValue("text");
        JsonNode text = textNode.get(0);
        String result = text.asText();

        return result;

    }
}


