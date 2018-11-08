package com.ititon.datacraw.model.location;

import java.util.HashMap;
import java.util.Map;

public final class ProvinceRegions {

    public enum Anshun {
        XIXIU("西秀区"),
        PINGBA("平坝区"),
        PUDING("普定县"),
        ZHENNING("镇宁布依族苗族自治县"),
        GUANLING("关岭布依族苗族自治县"),
        ZIYUN("紫云苗族布依族自治县"),
        UNKNOWN("");

        private static final Map<String, Anshun> regions = new HashMap<>();

        static {
            for (Anshun region : Anshun.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Anshun(String translation) {
            this.translation = translation;
        }

        public String getTranslation() {
            return translation;
        }

        public static Anshun findRegion(final String cnName) {
            return regions.get(cnName);
        }
    }

    public enum Bijie {
        QIXINGGUAN("七星关区"),
        QIANXI("黔西县"),
        ZHIJING("织金县"),
        NAYIONG("纳雍县"),
        WEINING("威宁彝族回族苗族自治县"),
        DAFANG("大方县"),
        JINGSHA("金沙县"),
        HEZHANG("赫章县"),
        UNKNOWN("");

        private static final Map<String, Bijie> regions = new HashMap<>();

        static {
            for (Bijie region : Bijie.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Bijie(String translation) {
            this.translation = translation;
        }


        public String getTranslation() {
            return translation;
        }

        public static Bijie findRegion(final String cnName) {
            return regions.get(cnName);
        }
    }

    public enum Guian {
        GUIAN("贵安新区"),
        UNKNOWN("");

        private static final Map<String, Guian> regions = new HashMap<>();

        static {
            for (Guian region : Guian.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Guian(String translation) {
            this.translation = translation;
        }

        public static Guian findRegion(final String cnName) {
            return regions.get(cnName);
        }

        public String getTranslation() {
            return translation;
        }

    }

    public enum Guiyang {
        YUNYAN("云岩区"),
        NANMING("南明区"),
        GUANSHUNHU("观山湖区"),
        WUDANG("乌当区"),
        HUAXI("花溪区"),
        BAIYUN("白云区"),
        XIFENG("息烽县"),
        XIUWEN("修文县"),
        KAIYANG("开阳县"),
        QINGZHEN("清镇市"),
        UNKNOWN("");

        private String translation;

        private static final Map<String, Guiyang> regions = new HashMap<>();

        static {
            for (Guiyang region : Guiyang.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        Guiyang(String translation) {
            this.translation = translation;
        }

        public String getTranslation() {
            return translation;
        }


        public static Guiyang findRegion(final String cnName) {
            return regions.get(cnName);
        }
    }

    public enum Liupanshui {
        ZHONGSHAN("钟山区"),
        LIUZHI("六枝特区"),
        SHUICHENG("水城县"),
        PANZHOU("盘州县"),
        UNKNOWN("");

        private static final Map<String, Liupanshui> regions = new HashMap<>();

        static {
            for (Liupanshui region : Liupanshui.values()) {
                regions.put(region.getTranslation(), region);
            }
        }


        private String translation;


        Liupanshui(String translation) {
            this.translation = translation;
        }


        public static Liupanshui findRegion(final String cnName) {
            return regions.get(cnName);
        }


        public String getTranslation() {
            return translation;
        }
    }

    public enum Qiandongnan {

        KAILI("凯里市"),
        HUANGPING("黄平县"),
        SHIBING("施秉县"),
        SANSHUI("三穗县"),
        ZHENYUAN("镇远县"),
        ZHENGONG("岑巩县"),
        TIANZHU("天柱县"),
        JINGPING("锦屏县"),
        JIANHE("剑河县"),
        TAIJIANG("台江县"),
        LIPING("黎平县"),
        RONGJIANG("榕江县"),
        CONGJIANG("从江县"),
        LEISHAN("雷山县"),
        MAJIANG("麻江县"),
        DAZAI("丹寨县"),
        UNKNOWN("");

        private static final Map<String, Qiandongnan> regions = new HashMap<>();

        static {
            for (Qiandongnan region : Qiandongnan.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Qiandongnan(String translation) {
            this.translation = translation;
        }

        public static Qiandongnan findRegion(final String cnName) {
            return regions.get(cnName);
        }

        public String getTranslation() {
            return translation;
        }
    }

    public enum Qiannan {
        DUYUN("都匀市"),
        FUQUAN("福泉县"),
        LIBO("荔波县"),
        GUIDING("贵定县"),
        WENGAN("瓮安县"),
        DUSHAN("独山县"),
        PINGTANG("平塘县"),
        LUODIAN("罗甸县"),
        CHANGSHUN("长顺县"),
        SHANDU("三都水族自治县"),
        LONGLI("龙里县"),
        HUISHUI("惠水县"),
        UNKNOWN("");

        private static final Map<String, Qiannan> regions = new HashMap<>();

        static {
            for (Qiannan region : Qiannan.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Qiannan(String translation) {
            this.translation = translation;
        }

        public static Qiannan findRegion(final String cnName) {
            return regions.get(cnName);
        }

        public String getTranslation() {
            return translation;
        }
    }

    public enum Qianxinan {
        XINGYI("兴义市"),
        XINGREN("兴仁县"),
        PUAN("普安县"),
        QINGLONG("晴隆县"),
        ZHENFENG("贞丰县"),
        WANGMO("望谟县"),
        CEHENG("册亨县"),
        ANLONG("安龙县"),
        UNKNOWN("");

        private static final Map<String, Qianxinan> regions = new HashMap<>();
        static {
            for (Qianxinan region : Qianxinan.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Qianxinan(String translation) {
            this.translation = translation;
        }

        public static Qianxinan findRegion(final String cnName) {
            return regions.get(cnName);
        }

        public String getTranslation() {
            return translation;
        }
    }

    public enum Tongren {
        BIJIANG("碧江区"),
        WANSHAN("万山区"),
        JIANGKOU("江口县"),
        YUPING("玉屏侗族自治县"),
        SINAN("思南县"),
        YINJIANG("印江土家族苗族自治县"),
        DEJIANG("德江县"),
        YANHE("沿河土家族自治县"),
        SHIQIAN("石阡县"),
        SONGTAO("松桃苗族自治县"),
        UNKNOWN("");

        private static final Map<String, Tongren> regions = new HashMap<>();
        static {
            for (Tongren region : Tongren.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        private String translation;

        Tongren(String translation) {
            this.translation = translation;
        }

        public String getTranslation() {
            return translation;
        }

        public static Tongren findRegion(final String cnName) {
            return regions.get(cnName);
        }
    }

    public enum Zunyi {
        HONGHUAGANG("红花岗区"),
        HUICHUAN("汇川区"),
        CISHUI("赤水市"),
        RENHUAI("仁怀市"),
        MEITAN("湄潭县"),
        BOZHOU("播州区"),
        TONGZI("桐梓县"),
        ZHENGAN("正安县"),
        DAOZHEN("道真仡佬苗族自治县"),
        WUCHUAN("务川仡佬苗族自治县"),
        FENGGANG("凤冈县"),
        YUQIN("余庆县"),
        SHUIYANG("绥阳县"),
        XISHUI("习水县"),
        UNKNOWN("");
        private static final Map<String, Zunyi> regions = new HashMap<>();
        static {
            for (Zunyi region : Zunyi.values()) {
                regions.put(region.getTranslation(), region);
            }
        }

        Zunyi(String translation) {
            this.translation = translation;
        }

        public static Zunyi findRegion(final String cnName) {
            return regions.get(cnName);
        }

        private String translation;

        public String getTranslation() {
            return translation;
        }
    }
}
