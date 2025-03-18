package com.jakubolejarczyk.vet_server.controller.common;

public class ChooseRoleController {
//    private final TokenService tokenService;
//    private final AccountService accountService;
//    private final OpeningHoursService openingHoursService;
//    private final VetService vetService;
//    private final ClientService clientService;
//
//    @PostMapping("choose-role")
//    public ResponseEntity<ResponseDto<String>> chooseRolePost(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        val token = requestDto.getToken();
//        val role = requestDto.getRole();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<>(false, messages, role);
//            return ResponseEntity.ok().body(responseDto);
//        }
//        accountService.updateRole(email, role);
//        if (role.equals("vet")) {
//            val openingHours = openingHoursService.create();
//            vetService.create(account.get().getId(), openingHours.getId());
//        }
//        else if (role.equals("client")) {
//            clientService.create(account.get().getId());
//        }
//        messages.add("The role has been chosen!");
//        val responseDto = new ResponseDto<>(true, messages, role);
//        return ResponseEntity.ok().body(responseDto);
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
