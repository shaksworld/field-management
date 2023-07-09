package com.shaks.fieldmanagementsystem.controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.shaks.fieldmanagementsystem.dto.*;
import com.shaks.fieldmanagementsystem.entity.Role;
import com.shaks.fieldmanagementsystem.entity.User;
import com.shaks.fieldmanagementsystem.repo.UserRepository;
import com.shaks.fieldmanagementsystem.service.UserService;
import com.shaks.fieldmanagementsystem.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.shaks.fieldmanagementsystem.util.ResponseConstants.ERROR;
import static com.shaks.fieldmanagementsystem.util.ResponseConstants.SUCCESS;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signup(@RequestBody @Valid SignupDTO signupDTO) {
        try {
            UserDTO user = userService.save(signupDTO);
            APIResponse<UserDTO> build = APIResponse.<UserDTO>builder()
                    .status(SUCCESS)
                    .results(user)
                    .build();
            return ResponseEntity.ok(build);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            APIResponse<String> build = APIResponse.<String>builder()
                    .status(ERROR)
                    .results("Username or Email already exists")
                    .build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(build);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse<String> build = APIResponse.<String>builder()
                    .status(ERROR)
                    .results("Something went wrong")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(build);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO, HttpServletRequest request) throws BadJOSEException, ParseException, JOSEException {

        TokenDTO tokenDTO = new TokenDTO();

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String accessToken = JwtUtil.createAccessToken(authentication.getName(), String.valueOf(request.getRequestURL()), roles);
            String refreshToken = JwtUtil.createRefreshToken(authentication.getName());
            long expirationTime = JwtUtil.getExpirationTime(accessToken).getTime() / 1000;

            tokenDTO.setAccess_token(accessToken);
            tokenDTO.setRefresh_token(refreshToken);
            tokenDTO.setExpires_in(expirationTime);
            tokenDTO.setToken_type("Bearer");

        } catch (AuthenticationException e) {
            if (e.getMessage().equals("Bad credentials")) {
                HashMap<String, String> error = new HashMap<>();
                error.put("error", "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "Something went wrong");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        return ResponseEntity.ok().body(tokenDTO);

    }


    @PostMapping("/addRole")
    public ResponseEntity<APIResponse<?>> addRole(@RequestParam("role") String role, Principal principal) {
        String username = principal.getName();
        UserDTO user = userService.addRoleToUser(username, role);
        return ResponseEntity.ok(APIResponse.builder()
                .results(user)
                .status(SUCCESS)
                .build());
    }


    @PostMapping("/refresh")
    public ResponseEntity refresh(@RequestParam(value = "refreshToken") String refreshToken, HttpServletRequest request) {
        TokenDTO tokenDTO = new TokenDTO();
        try {
            UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(refreshToken);
            String username = authenticationToken.getName();
            User user = userRepository.findByUsername(username);
            List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
            String accessToken = JwtUtil.createAccessToken(username, request.getServletPath(), roles);
            String newRefreshToken = JwtUtil.createRefreshToken(username);
            long expirationTime = JwtUtil.getExpirationTime(accessToken).getTime() / 1000;

            tokenDTO.setAccess_token(accessToken);
            tokenDTO.setRefresh_token(newRefreshToken);
            tokenDTO.setExpires_in(expirationTime);
            tokenDTO.setToken_type("Bearer");
        } catch (Exception e) {
            if (e.getMessage().equals("Expired JWT token")) {
                APIResponse<String> error = APIResponse.<String>builder()
                        .status(ERROR)
                        .results("Refresh token expired")
                        .build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED
                ).body(error);
            } else if (e.getMessage().equals("Invalid JWT token")) {
                APIResponse<String> error = APIResponse.<String>builder()
                        .status(ERROR)
                        .results("Invalid refresh token")
                        .build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED
                ).body(error);
            } else if (e.getMessage().equals("Error to parse JWT")) {
                APIResponse<String> error = APIResponse.<String>builder()
                        .status(ERROR)
                        .results("Invalid refresh token")
                        .build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED
                ).body(error);
            } else {
                APIResponse<String> error = APIResponse.<String>builder()
                        .status(ERROR)
                        .results(e.getMessage())
                        .build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED
                ).body(error);
            }
        }

        return ResponseEntity.ok().body(tokenDTO);
    }

}
