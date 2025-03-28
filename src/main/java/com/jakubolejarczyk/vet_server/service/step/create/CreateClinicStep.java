//package com.jakubolejarczyk.vet_server.service.step.create;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
//import com.jakubolejarczyk.vet_server.model.independent.Account;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
//import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
//import com.jakubolejarczyk.vet_server.service.input.create.CreateClinicInput;
//import com.jakubolejarczyk.vet_server.service.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.model.StepOutput;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CreateClinicStep implements StepModel<CreateClinicInput, Clinic> {
//    private final OpeningHoursService openingHoursService;
//    private final ClinicService clinicService;
//
//    @Override
//    public StepOutput<Clinic> runStep(CreateClinicInput input) {
//        try {
//            val openingHoursId = input.openingHoursId();
//            val openingHours = openingHoursService.findById(openingHoursId);
//            if (openingHours.isEmpty()) {
//                return StepOutput.<Clinic>builder()
//                        .success(false)
//                        .build();
//            }
//            val name = input.name();
//            val street = input.street();
//            val buildingNumber = input.buildingNumber();
//            val apartmentNumber = input.apartmentNumber();
//            val postalCode = input.postalCode();
//            val city = input.city();
//            val province = input.province();
//            val country = input.country();
//            val email = input.email();
//            val phoneNumber = input.phoneNumber();
//            val clinic = Clinic.builder()
//                    .name(name)
//                    .street(street)
//                    .buildingNumber(buildingNumber)
//                    .apartmentNumber(apartmentNumber)
//                    .postalCode(postalCode)
//                    .city(city)
//                    .province(province)
//                    .country(country)
//                    .email(email)
//                    .phoneNumber(phoneNumber)
//                    .isArchived(false)
//                    .openingHoursId(openingHoursId)
//                    .build();
//            val newClinic = clinicService.create(clinic);
//            return StepOutput.<Clinic>builder()
//                    .success(true)
//                    .output(newClinic)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
