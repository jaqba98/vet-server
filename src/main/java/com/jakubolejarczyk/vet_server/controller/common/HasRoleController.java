package com.jakubolejarczyk.vet_server.controller.common;

public class HasRoleController {
//    private final TokenService tokenService;
//
//    private final AccountService accountService;
//
//    @PostMapping("has-role")
//    public ResponseEntity<HasRoleResponseDto> hasRole(@Valid @RequestBody HasRoleRequestDto requestDto) {
//        String token = requestDto.getToken();
//        String email = tokenService.decode(token);
//        Optional<Account> account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            HasRoleResponseDto responseDto = new HasRoleResponseDto(false, new ArrayList<>());
//            return ResponseEntity.ok().body(responseDto);
//        }
//        String role = account.get().getRole();
//        if (role == null) {
//            HasRoleResponseDto responseDto = new HasRoleResponseDto(false, new ArrayList<>());
//            return ResponseEntity.ok().body(responseDto);
//        }
//        HasRoleResponseDto responseDto = new HasRoleResponseDto(true, new ArrayList<>());
//        return ResponseEntity.ok().body(responseDto);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<HasRoleResponseDto> handleValidation(MethodArgumentNotValidException ex) {
//        ArrayList<String> errors = new ArrayList<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String message = error.getDefaultMessage();
//            errors.add(message);
//        });
//        HasRoleResponseDto responseDto = new HasRoleResponseDto(false, errors);
//        return ResponseEntity.ok().body(responseDto);
//    }
}
