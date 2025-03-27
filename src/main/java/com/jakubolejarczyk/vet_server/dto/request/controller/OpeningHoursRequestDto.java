//package com.jakubolejarczyk.vet_server.dto.request.controller;
//
//import com.jakubolejarczyk.vet_server.domain.independent.OpeningHoursDomain;
//import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalTime;
//
//@Getter
//@Setter
//public class OpeningHoursRequestDto extends TokenRequestDto implements OpeningHoursDomain {
//    private Long id;
//
//    private LocalTime mondayFrom;
//
//    private LocalTime mondayTo;
//
//    private LocalTime tuesdayFrom;
//
//    private LocalTime tuesdayTo;
//
//    private LocalTime wednesdayFrom;
//
//    private LocalTime wednesdayTo;
//
//    private LocalTime thursdayFrom;
//
//    private LocalTime thursdayTo;
//
//    private LocalTime fridayFrom;
//
//    private LocalTime fridayTo;
//
//    private LocalTime saturdayFrom;
//
//    private LocalTime saturdayTo;
//
//    private LocalTime sundayFrom;
//
//    private LocalTime sundayTo;
//
//    @NotNull(message = "Is archived is required!")
//    private Boolean isArchived;
//}
