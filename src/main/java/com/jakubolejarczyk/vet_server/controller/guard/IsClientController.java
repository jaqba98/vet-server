package com.jakubolejarczyk.vet_server.controller.guard;

public class IsClientController {
//    private final TokenService tokenService;
//
//    private final AccountService accountService;
//
//    @PostMapping("is-client")
//    public ResponseEntity<IsClientResponseDto> isClient(@Valid @RequestBody IsClientRequestDto requestDto) {
//        String token = requestDto.getToken();
//        String email = tokenService.decode(token);
//        Optional<Account> account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            IsClientResponseDto responseDto = new IsClientResponseDto(false, new ArrayList<>());
//            return ResponseEntity.ok().body(responseDto);
//        }
//        String role = account.get().getRole();
//        Boolean isClient = role != null && role.contains("client");
//        IsClientResponseDto responseDto = new IsClientResponseDto(isClient, new ArrayList<>());
//        return ResponseEntity.ok().body(responseDto);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<IsClientResponseDto> handleValidation(MethodArgumentNotValidException ex) {
//        ArrayList<String> errors = new ArrayList<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String message = error.getDefaultMessage();
//            errors.add(message);
//        });
//        IsClientResponseDto responseDto = new IsClientResponseDto(false, errors);
//        return ResponseEntity.ok().body(responseDto);
//    }
}
