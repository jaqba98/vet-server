package com.jakubolejarczyk.vet_server.controller.common;

public class LoginController {
//    private final AccountService accountService;
//
//    private final PasswordService passwordService;
//
//    private final TokenService tokenService;
//
//    @PostMapping("login")
//    public ResponseEntity<ResponseDto<String>> login(@RequestBody LoginRequestDto requestDto) {
//        val email = requestDto.getEmail();
//        val password = requestDto.getPassword();
//        val account = accountService.findByEmail(email);
//        val errors = new ArrayList<String>();
//        if (account.isEmpty()) {
//            errors.add("Incorrect email or password!");
//            val responseDto = new ResponseDto<>(false, errors, "");
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val encodedPassword = account.get().getPassword();
//        if (!passwordService.match(password, encodedPassword)) {
//            errors.add("Incorrect email or password!");
//            val responseDto = new ResponseDto<>(false, errors, "");
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        String token = tokenService.generate(email);
//        val responseDto = new ResponseDto<>(true, errors, token);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
}
