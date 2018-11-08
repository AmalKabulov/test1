package com.ititon.datacraw.model.location;


import com.ititon.datacraw.model.location.ProvinceRegions.*;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Province {

    GUIYANG("贵阳市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Guiyang region : Guiyang.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    ZUNYI("遵义市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Zunyi region : Zunyi.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    ANSHUN("安顺市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Anshun region : Anshun.values()) {
                regions.put(region.getTranslation(), region.name());
            }
            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    BIJIE("毕节市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Bijie region : Bijie.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    TONGREN("铜仁市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Tongren region : Tongren.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    LIUPANSHUI("六盘水市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Liupanshui region : Liupanshui.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    QIANXINAN("黔西南布依族苗族自治州") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Qianxinan region : Qianxinan.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },

    QIANDONGNAN("黔东南苗族侗族自治州") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;

        private final Map<String, String> regions = new HashMap<>();
        {
            for (Qiandongnan region : Qiandongnan.values()) {
                regions.put(region.getTranslation(), region.name());
            }
            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    QIANNAN("黔南布依族苗族自治州") {

        private final Map<String, String> regions = new HashMap<>();
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        {
            for (Qiannan region : Qiannan.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    GUIAN("贵安新区") {

        private final Map<String, String> regions = new HashMap<>();
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        {
            for (Guian region : Guian.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            String region = regions.get(cnName);
            if (region == null) {

                for (String s : regions.keySet()) {
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    if (cnName.contains(s)) {
                        return regions.get(s);
                    }
                }

            }
            return region;
        }
    },
    UNKNOWN("Unknown") {
        @Override
        public Integer getMinRegionNameLength() {
            return 0;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return 0;
        }

        public String findRegionName(final String cnName) {
            return "Unknown";
        }
    };

    private static final Map<String, Province> provinces = new HashMap<>();

    static {
        for (Province province : Province.values()) {
            provinces.put(province.getTranslation(), province);
        }
    }

    private String translation;

    Province(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static Province findProvince(final String cnProvinceName) {
        return provinces.get(cnProvinceName);
    }

    public abstract String findRegionName(final String cnName);

    public abstract Integer getMinRegionNameLength();

    public abstract Integer getMaxRegionNameLength();


}
