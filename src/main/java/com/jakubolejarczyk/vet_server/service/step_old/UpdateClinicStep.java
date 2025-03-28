//package com.jakubolejarczyk.vet_server.service.step_old;
//
//import com.jakubolejarczyk.vet_server.domain.dependent.ClinicDomain;
//import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateClinicStep {
//    private final ClinicService clinicService;
//
//    public StepModel<Clinic> runStep(ResponseStep responseStep, ClinicDomain requestDto, ClinicDomain currentClinic) throws Exception {
//        try {
//            val clinic = com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder()
//                    .id(currentClinic.getId())
//                    .name(requestDto.getName())
//                    .street(requestDto.getStreet())
//                    .buildingNumber(requestDto.getBuildingNumber())
//                    .apartmentNumber(requestDto.getApartmentNumber())
//                    .postalCode(requestDto.getPostalCode())
//                    .city(requestDto.getCity())
//                    .province(requestDto.getProvince())
//                    .country(requestDto.getCountry())
//                    .email(requestDto.getEmail())
//                    .phoneNumber(requestDto.getPhoneNumber())
//                    .isArchived(requestDto.getIsArchived())
//                    .openingHoursId(currentClinic.getOpeningHoursId())
//                    .build();
//            clinicService.update(clinic);
//            return StepModel.<Clinic>builder()
//                    .error(false)
//                    .data(clinic)
//                    .build();
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
//}
