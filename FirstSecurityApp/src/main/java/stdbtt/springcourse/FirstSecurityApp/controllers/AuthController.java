package stdbtt.springcourse.FirstSecurityApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stdbtt.springcourse.FirstSecurityApp.dto.AuthenticationDTO;
import stdbtt.springcourse.FirstSecurityApp.dto.PersonDTO;
import stdbtt.springcourse.FirstSecurityApp.models.Person;
import stdbtt.springcourse.FirstSecurityApp.security.JWTUtil;
import stdbtt.springcourse.FirstSecurityApp.services.RegistrationService;
import stdbtt.springcourse.FirstSecurityApp.util.PersonValidator;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    /*@GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }
*/
    @PostMapping("/registration")
    public Map<String,String> performRegistration(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        Person person = convertToPerson(personDTO);

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return Map.of("message", "Ошибка!!!");
        }
        registrationService.register(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getUsername(),
                        authenticationDTO.getPassword()
                );
        try{
            authenticationManager.authenticate(authInputToken);
        }catch (BadCredentialsException e){
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);

    }

    public Person convertToPerson(PersonDTO personDTO){
        return this.modelMapper.map(personDTO, Person.class);
    }
}
