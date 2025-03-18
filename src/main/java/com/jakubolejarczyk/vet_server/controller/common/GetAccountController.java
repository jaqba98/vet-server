package com.jakubolejarczyk.vet_server.controller.common;

public class GetAccountController {
//    private final AccountService accountService;
//
//    private final TokenService tokenService;
//
//    @PostMapping("get-account")
//    public ResponseEntity<GetAccountResponseDto> getAccountPost(@RequestBody GetAccountRequestDto requestDto) {
//        String token = requestDto.getToken();
//        String email = tokenService.decode(token);
//        Optional<Account> account = accountService.findByEmail(email);
//        if (account.isPresent()) {
//            String firstName = account.get().getFirstName();
//            String lastName = account.get().getLastName();
//            GetAccountResponseDto responseDto = GetAccountResponseDto.builder()
//                    .success(true)
//                    .firstName(firstName)
//                    .lastName(lastName)
//                    .build();
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        GetAccountResponseDto responseDto = GetAccountResponseDto.builder()
//                .success(false)
//                .firstName("")
//                .lastName("")
//                .build();
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
}
