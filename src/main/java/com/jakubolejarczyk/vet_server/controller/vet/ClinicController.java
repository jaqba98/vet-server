package com.jakubolejarczyk.vet_server.controller.vet;

public class ClinicController {
//    private final OpeningHoursService openingHoursService;
//    private final ClinicService clinicService;
//    private final TokenService tokenService;
//    private final AccountService accountService;
//    private final OwnerService ownerService;
//    private final ClinicAccountService clinicAccountService;
//
//    @PostMapping("create")
//    public ResponseEntity<ResponseDto<Clinic>> create(@Valid @RequestBody ClinicRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        // Create an OpeningHours object
//        val openingHours = openingHoursService.create();
//        // Create a Clinic object
//        val clinic = Clinic.builder()
//                .name(requestDto.getName())
//                .street(requestDto.getStreet())
//                .buildingNumber(requestDto.getBuildingNumber())
//                .apartmentNumber(requestDto.getApartmentNumber())
//                .postalCode(requestDto.getPostalCode())
//                .city(requestDto.getCity())
//                .province(requestDto.getProvince())
//                .country(requestDto.getCountry())
//                .email(requestDto.getEmail())
//                .phoneNumber(requestDto.getPhoneNumber())
//                .openingHoursId(openingHours.getId())
//                .build();
//        val newClinic = clinicService.create(clinic);
//        // Determine account id
//        val token = requestDto.getToken();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<>(false, messages, clinic);
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val accountId = account.get().getId();
//        // Create an Owner object
//        ownerService.create(accountId, newClinic.getId());
//        // Create a Clinic_Account object
//        clinicAccountService.create(accountId, newClinic.getId());
//        // Return the response dto
//        messages.add("The clinic has been established successfully!");
//        val responseDto = new ResponseDto<>(true, messages, clinic);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
//
//    @PostMapping("read")
//    public ResponseEntity<ResponseDto<ArrayList<Clinic>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        val clinics = new ArrayList<Clinic>();
//        val token = requestDto.getToken();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<>(false, messages, clinics);
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val accountId = account.get().getId();
//        val clinicIds = clinicAccountService.findByAccountId(accountId)
//                .stream()
//                .map(ClinicAccount::getClinicId).collect(Collectors.toCollection(ArrayList::new));
//        val matchedClinics = new ArrayList<>(clinicService.findAllById(clinicIds));
//        messages.add("The clinics have been read successfully!");
//        ResponseDto<ArrayList<Clinic>> responseDto = new ResponseDto<>(true, messages, matchedClinics);
//        return ResponseEntity.ok().body(responseDto);
//    }
//
//    @PostMapping("update")
//    public ResponseEntity<ResponseDto<Clinic>> update(@Valid @RequestBody ClinicRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        val token = requestDto.getToken();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<Clinic>(false, messages, Clinic.builder().build());
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val accountId = account.get().getId();
//        val clinicId = requestDto.getId();
//        val relation = clinicAccountService.findByAccountIdAndClinicId(accountId, clinicId);
//        if (relation.isEmpty()) {
//            messages.add("You do not have permission to update!");
//            val responseDto = new ResponseDto<Clinic>(false, messages, Clinic.builder().build());
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        Optional<Clinic> clinic = clinicService.findById(clinicId);
//        if (clinic.isEmpty()) {
//            messages.add("The clinic does not exist!");
//            val responseDto = new ResponseDto<Clinic>(false, messages, Clinic.builder().build());
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val clinicToUpdate = clinic.get();
//        clinicToUpdate.setName(requestDto.getName());
//        clinicToUpdate.setStreet(requestDto.getStreet());
//        clinicToUpdate.setBuildingNumber(requestDto.getBuildingNumber());
//        clinicToUpdate.setApartmentNumber(requestDto.getApartmentNumber());
//        clinicToUpdate.setPostalCode(requestDto.getPostalCode());
//        clinicToUpdate.setCity(requestDto.getCity());
//        clinicToUpdate.setProvince(requestDto.getProvince());
//        clinicToUpdate.setCountry(requestDto.getCountry());
//        clinicToUpdate.setEmail(requestDto.getEmail());
//        clinicToUpdate.setPhoneNumber(requestDto.getPhoneNumber());
//        clinicService.update(clinicToUpdate);
//        messages.add("The clinic has been updated successfully!");
//        val responseDto = new ResponseDto<>(true, messages, clinicToUpdate);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
//
//    @PostMapping("delete")
//    public ResponseEntity<ResponseDto<Boolean>> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        val token = requestDto.getToken();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<>(false, messages, false);
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val accountId = account.get().getId();
//        val clinicIds = requestDto.getIds();
//        val clinicAccountRelations = clinicAccountService.findByAccountIdAndClinicIdIn(accountId, clinicIds);
//        clinicAccountService.deleteAllInBatch(clinicAccountRelations);
//        val ownerRelations = ownerService.findByAccountIdAndClinicIdIn(accountId, clinicIds);
//        ownerService.deleteAllInBatch(ownerRelations);
//        clinicService.deleteByIds(clinicIds);
//        val openingHoursIds = clinicService.findAllById(clinicIds).stream().map(Clinic::getOpeningHoursId).toList();
//        openingHoursService.deleteAllByIdInBatch(openingHoursIds);
//        messages.add("Clinics were deleted successfully!");
//        val responseDto = new ResponseDto<>(true, messages, true);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseDto<String>> handleValidation(MethodArgumentNotValidException ex) {
//        val errors = new ArrayList<String>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String message = error.getDefaultMessage();
//            errors.add(message);
//        });
//        val responseDto = new ResponseDto<>(false, errors, "");
//        return ResponseEntity.ok().body(responseDto);
//    }
}
