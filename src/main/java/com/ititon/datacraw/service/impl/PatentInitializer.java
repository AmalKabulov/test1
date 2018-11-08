//package com.ititon.datacraw.service.impl;
//
//import com.ititon.datacraw.model.*;
//import com.ititon.datacraw.model.location.City;
//import com.ititon.datacraw.model.location.District;
//import com.ititon.datacraw.model.location.Province;
//import com.ititon.datacraw.repository.*;
//import com.ititon.datacraw.service.FieldInitializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//public class PatentInitializer {
//
//    private final Map<SearchField, FieldInitializer> initializers = new HashMap<>();
//
//    {
//        initializers.put(SearchField.NAME, new NameInitializer());
//        initializers.put(SearchField.APPLICATION_NUM, new ApplicationNumInitializer());
//        initializers.put(SearchField.APPLICATION_NUM1, new ApplicationNumInitializer());
//        initializers.put(SearchField.APPLICATION_DATE, new ApplicationDateInitializer());
//        initializers.put(SearchField.PUBLICATION_NUM, new PublicationNumInitializer());
//        initializers.put(SearchField.PUBLICATION_DATE, new PublicationDateInitializer());
//        initializers.put(SearchField.APPLICANT, new ApplicantInitializer());
//        initializers.put(SearchField.HOLDER, new HolderInitializer());
//        initializers.put(SearchField.LA_NUM, new LANumInitializer());
//        initializers.put(SearchField.AGENT, new AgentInitializer());
//        initializers.put(SearchField.AGENCY, new AgencyInitializer());
//        initializers.put(SearchField.PRIORITY_NUM, new PriorityNumInitializer());
//        initializers.put(SearchField.PRIORITY_DATE, new PriorityDateInitializer());
//        initializers.put(SearchField.APPLICANT_ADDRESS, new ApplicantAddressInitializer());
//        initializers.put(SearchField.APPLICANT_INDEX, new ApplicantIndexInitializer());
//        initializers.put(SearchField.APPLICANT_PROVINCE, new ApplicantProvinceInitializer());
//        initializers.put(SearchField.CPC_NUM, new CpcNumInitializer());
//        initializers.put(SearchField.LEGAL_STATUS, new LegalStatusInitializer());
//        initializers.put(SearchField.SOURCE_OF, new SourceOfInitializer());
//        initializers.put(SearchField.PATENT_TYPE, new PatentTypeInitializer());
//    }
//
//    private AgencyRepository agencyRepository;
//    private AgentRepository agentRepository;
//    private ApplicantRepository applicantRepository;
//    private HolderRepository holderRepository;
//    private LegalStatusRepository legalStatusRepository;
//    private PatentTypeRepository patentTypeRepository;
//    private DistrictRepository districtRepository;
//    private CitiesRepository citiesRepository;
//
//    private LocationFinder locationFinder;
//
//
//    @Autowired
//    public PatentInitializer(AgencyRepository agencyRepository, AgentRepository agentRepository, ApplicantRepository applicantRepository, HolderRepository holderRepository, LegalStatusRepository legalStatusRepository, PatentTypeRepository patentTypeRepository, DistrictRepository districtRepository, CitiesRepository citiesRepository, LocationFinder locationFinder) {
//        this.agencyRepository = agencyRepository;
//        this.agentRepository = agentRepository;
//        this.applicantRepository = applicantRepository;
//        this.holderRepository = holderRepository;
//        this.legalStatusRepository = legalStatusRepository;
//        this.patentTypeRepository = patentTypeRepository;
//        this.districtRepository = districtRepository;
//        this.citiesRepository = citiesRepository;
//        this.locationFinder = locationFinder;
//    }
//
//    @SuppressWarnings("unchecked")
//    public <Type extends Serializable> void initializePatentField(final Patent patent, final String fieldName, final Type initFieldValue) {
//        final SearchField field = SearchField.findByValue(fieldName);
//        final FieldInitializer fieldInitializer = initializers.get(field);
//        if (fieldInitializer != null) {
//            fieldInitializer.setValue(patent, initFieldValue);
//        }
//    }
//
//    private class NameInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setNameNative(initValue);
//        }
//    }
//
//    private class ApplicationNumInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setApplicationNum(initValue);
//        }
//    }
//
//    private class ApplicationDateInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            LocalDate date = LocalDate.parse(initValue.replaceAll("[.]+", "-"));
//            patent.setApplicationDate(date);
//        }
//    }
//
//    private class PublicationNumInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setPublicationNum(initValue);
//        }
//    }
//
//    private class PublicationDateInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            LocalDate date = LocalDate.parse(initValue.replaceAll("[.]+", "-"));
//            patent.setPublicationDate(date);
//        }
//    }
//
//    private class ApplicantInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            final Applicant applicant = applicantRepository.findByName(initValue).orElseGet(() -> {
//                Applicant newApplicant = new Applicant();
//                newApplicant.setName(initValue);
//                return applicantRepository.save(newApplicant);
//            });
//
//            patent.setApplicant(applicant);
//        }
//    }
//
//    private class HolderInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            final Holder holder = holderRepository.findByName(initValue).orElseGet(() -> {
//                Holder newHolder = new Holder();
//                newHolder.setName(initValue);
//                return holderRepository.save(newHolder);
//            });
//
//            patent.setHolder(holder);
//        }
//    }
//
//    private class LANumInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setLaNum(initValue);
//        }
//    }
//
//    private class AgentInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//
//            String agentName = initValue.replace(";", "").trim();
//
//            if (!agentName.isEmpty()) {
//                final Agent agent = agentRepository.findByName(agentName).orElseGet(() -> {
//                    Agent newAgent = new Agent();
//                    newAgent.setName(agentName);
//                    return agentRepository.save(newAgent);
//                });
//                patent.setAgent(agent);
//            }
//        }
//    }
//
//    private class AgencyInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//
//            String agencyName = initValue.replace(";", "").trim();
//            if (!agencyName.isEmpty()) {
//                final Agency agency = agencyRepository.findByName(agencyName).orElseGet(() -> {
//                    Agency newAgency = new Agency();
//                    newAgency.setName(agencyName);
//                    return agencyRepository.save(newAgency);
//                });
//                patent.setAgency(agency);
//            }
//        }
//    }
//
//    private class PriorityNumInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setPriorityNum(initValue);
//        }
//    }
//
//    private class PriorityDateInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            if (initValue != null && !initValue.isEmpty()) {
//                LocalDate date = LocalDate.parse(initValue.replaceAll("[.]+", "-"));
//                patent.setPriorityDate(date);
//            }
//        }
//    }
//
//    private class ApplicantAddressInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            final Applicant applicant = patent.getApplicant();
//            final Province province = locationFinder.findProvince(initValue);
//
//            City city = citiesRepository.findByNameEn(StringUtils.capitalize(province.name().toLowerCase()))
//                    .orElseThrow(() -> new RuntimeException("Province not found in db"));
//
//            String district = locationFinder.findRegion(province, initValue);
//            final District districtFromDb = districtRepository.findByNameEnAndAndCity(district, city)
//                    .orElseThrow(() -> new RuntimeException("Distrinct not found in db"));
//
//            if (Objects.equals(district, "Unknown")){
//                district = "";
//            }
//            final String address = locationFinder.findAddress(province, district, initValue);
//
//            applicant.setDistrict(districtFromDb);
//            applicant.setAddress(address);
//
//            applicantRepository.save(applicant);
//        }
//    }
//
//    private class ApplicantIndexInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            Applicant applicant = patent.getApplicant();
//            applicant.setIndex(initValue);
//
//            applicantRepository.save(applicant);
//        }
//    }
//
//
//    private class CpcNumInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setCpcNum(initValue);
//        }
//    }
//
//
//    private class LegalStatusInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            LegalStatus legalStatus = legalStatusRepository.findByStatus(initValue).orElseGet(() -> {
//                LegalStatus newLegalStatus = new LegalStatus();
//                newLegalStatus.setStatus(initValue);
//                return legalStatusRepository.save(newLegalStatus);
//            });
//
//            patent.setLegalStatus(legalStatus);
//        }
//    }
//
//    private class SourceOfInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            patent.setSourceOf(initValue);
//        }
//    }
//
//
//    private class PatentTypeInitializer implements FieldInitializer<String> {
//        @Override
//        public void setValue(final Patent patent, final String initValue) {
//            PatentType patentType = patentTypeRepository.findByType(initValue).orElseGet(() -> {
//                PatentType newPatentType = new PatentType();
//                newPatentType.setType(initValue);
//                return patentTypeRepository.save(newPatentType);
//            });
//
//            patent.setPatentType(patentType);
//        }
//    }
//
//
//    private class ApplicantProvinceInitializer implements FieldInitializer {
//        @Override
//        public void setValue(Patent patent, Serializable initValue) {
//
//        }
//    }
//}
