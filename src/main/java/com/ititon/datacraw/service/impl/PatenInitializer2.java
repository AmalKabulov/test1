package com.ititon.datacraw.service.impl;

import com.ititon.datacraw.model.*;
import com.ititon.datacraw.model.location.City;
import com.ititon.datacraw.model.location.District;
import com.ititon.datacraw.model.location.Province;
import com.ititon.datacraw.repository.*;
import com.ititon.datacraw.service.FieldInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatenInitializer2 {

    private final Map<PatentPageFields, FieldInitializer> initializers = new HashMap<>();

    {
        initializers.put(PatentPageFields.NAME, new NameInitializer());
        initializers.put(PatentPageFields.APPLICATION_NUM_FULL, new ApplicationNumFullInitializer());
        initializers.put(PatentPageFields.APPLICATION_NUM_SHORT, new ApplicationNumShortInitializer());
        initializers.put(PatentPageFields.APPLICATION_DATE, new ApplicationDateInitializer());
        initializers.put(PatentPageFields.PUBLICATION_NUM, new PublicationNumInitializer());
        initializers.put(PatentPageFields.PUBLICATION_DATE, new PublicationDateInitializer());
        initializers.put(PatentPageFields.APPLICANT, new ApplicantInitializer());
        initializers.put(PatentPageFields.LA_NUM, new LANumInitializer());
        initializers.put(PatentPageFields.AGENT, new AgentInitializer());
        initializers.put(PatentPageFields.AGENCY, new AgencyInitializer());

        initializers.put(PatentPageFields.APPLICANT_ADDRESS, new ApplicantAddressInitializer());
        initializers.put(PatentPageFields.APPLICANT_INDEX, new ApplicantIndexInitializer());
        initializers.put(PatentPageFields.APPLICANT_PROVINCE, new ApplicantProvinceInitializer());
        initializers.put(PatentPageFields.LEGAL_STATUS, new LegalStatusInitializer());
        initializers.put(PatentPageFields.IPC_NUM, new IPCNumInitializer());
    }


    private AgencyRepository agencyRepository;
    private AgentRepository agentRepository;
    private ApplicantRepository applicantRepository;
    private LegalStatusRepository legalStatusRepository;
    private DistrictRepository districtRepository;
    private CitiesRepository citiesRepository;
    private PatentRepository patentRepository;
    private LocationFinder locationFinder;

    @Autowired
    public PatenInitializer2(AgencyRepository agencyRepository, AgentRepository agentRepository, ApplicantRepository applicantRepository, LegalStatusRepository legalStatusRepository, DistrictRepository districtRepository, CitiesRepository citiesRepository, PatentRepository patentRepository, LocationFinder locationFinder) {
        this.agencyRepository = agencyRepository;
        this.agentRepository = agentRepository;
        this.applicantRepository = applicantRepository;
        this.legalStatusRepository = legalStatusRepository;
        this.districtRepository = districtRepository;
        this.citiesRepository = citiesRepository;
        this.patentRepository = patentRepository;
        this.locationFinder = locationFinder;
    }

    public void initAndSave(Map<String, String> patentValues) {
        Patent patent = new Patent();
        for (Map.Entry<String, String> keyValue : patentValues.entrySet()) {
            String key = keyValue.getKey();
            final PatentPageFields field = PatentPageFields.findByValue(key);
            final FieldInitializer fieldInitializer = initializers.get(field);
            if (fieldInitializer != null) {
                fieldInitializer.setValue(patent, keyValue.getValue());
            }
        }

        Patent byPublicationNum = patentRepository.findByPublicationNum(patent.getPublicationNum());
        if (byPublicationNum == null) {
            patentRepository.save(patent);
        }else {
            System.out.println("FOUND in DB : " + patent.getPublicationNum());
        }
    }

    private class NameInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            patent.setNameNative(initValue);
        }
    }

    private class ApplicationNumFullInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            patent.setApplicationFullNum(initValue);
        }
    }

    private class ApplicationNumShortInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            patent.setApplicationNum(initValue);
        }
    }

    private class ApplicationDateInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            LocalDate date = LocalDate.parse(initValue.replaceAll("[.]+", "-"));
            patent.setApplicationDate(date);
        }
    }

    private class PublicationNumInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            patent.setPublicationNum(initValue);
        }
    }

    private class PublicationDateInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            LocalDate date = LocalDate.parse(initValue.replaceAll("[.]+", "-"));
            patent.setPublicationDate(date);
        }
    }


    private class ApplicantInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            String replaced = initValue.replace("<FONT>", "");
            String correctValue = replaced.replace("</FONT>", "");

            final Applicant applicant = applicantRepository.findByName(correctValue).orElseGet(() -> {
                Applicant newApplicant = new Applicant();
                newApplicant.setName(correctValue);
                return applicantRepository.save(newApplicant);
            });

            patent.setApplicant(applicant);
        }
    }

    private class LANumInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            patent.setLaNum(initValue);
        }
    }

    private class ApplicantAddressInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            final Applicant applicant = patent.getApplicant();
            final Province province = locationFinder.findProvince(initValue);

            Optional<City> city = citiesRepository.findByNameEn(StringUtils.capitalize(province.name().toLowerCase()));

            if (!city.isPresent()) {
                applicant.setAddress(initValue);
                applicantRepository.save(applicant);
                return;
            }
//
//            if (!city.get().getNameNative().equalsIgnoreCase(initValue)) {
//                applicant.setAddress(initValue);
//                applicantRepository.save(applicant);
//                return;
//            }

            String district = locationFinder.findRegion(province, initValue);
            System.out.println("CITY " + city.get().getNameEn() + " REGION " + district);
            final District districtFromDb = districtRepository.findByNameEnAndAndCity(district, city.get())
                    .orElseThrow(() -> new RuntimeException("Distrinct not found in db"));

            if (Objects.equals(district, "Unknown")) {
                district = "";
            }
            final String address = locationFinder.findAddress(province, district, initValue);

            applicant.setDistrict(districtFromDb);
            applicant.setAddress(address);

            applicantRepository.saveAndFlush(applicant);
        }
    }

    private class ApplicantIndexInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            Applicant applicant = patent.getApplicant();
            applicant.setIndex(initValue);

            applicantRepository.save(applicant);
        }
    }

    private class LegalStatusInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            LegalStatus legalStatus = legalStatusRepository.findByStatus(initValue).orElseGet(() -> {
                LegalStatus newLegalStatus = new LegalStatus();
                newLegalStatus.setStatus(initValue);
                return legalStatusRepository.save(newLegalStatus);
            });

            patent.setLegalStatus(legalStatus);
        }
    }

    private class ApplicantProvinceInitializer implements FieldInitializer {
        @Override
        public void setValue(Patent patent, Serializable initValue) {

        }
    }

    private class AgentInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {

            if (initValue != null) {
                String agentName = initValue.replace(";", "").trim();

                if (!agentName.isEmpty()) {
                    final Agent agent = agentRepository.findByName(agentName).orElseGet(() -> {
                        Agent newAgent = new Agent();
                        newAgent.setName(agentName);
                        return agentRepository.save(newAgent);
                    });
                    patent.setAgent(agent);
                }
            }
        }
    }

    private class AgencyInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {

            if (initValue != null) {
                String agencyName = initValue.replace(";", "").trim();
                if (!agencyName.isEmpty()) {
                    final Agency agency = agencyRepository.findByName(agencyName).orElseGet(() -> {
                        Agency newAgency = new Agency();
                        newAgency.setName(agencyName);
                        return agencyRepository.save(newAgency);
                    });
                    patent.setAgency(agency);
                }
            }
        }
    }


    private class IPCNumInitializer implements FieldInitializer<String> {
        @Override
        public void setValue(final Patent patent, final String initValue) {
            patent.setIpcNum(initValue);
        }
    }
}
