package cl.cristian.rosales.test.services.impl;

import cl.cristian.rosales.test.dto.PhoneDTO;
import cl.cristian.rosales.test.dto.ResponseDTO;
import cl.cristian.rosales.test.dto.UserDTO;
import cl.cristian.rosales.test.exception.UserException;
import cl.cristian.rosales.test.models.Phone;
import cl.cristian.rosales.test.models.User;
import cl.cristian.rosales.test.repository.IUserRepository;
import cl.cristian.rosales.test.services.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {

    Logger logger = LogManager.getLogger(UserService.class);
    private final IUserRepository repository;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public static final String REGEX_EMAIL = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{0,9}$";
    @Value("${jwt.secret.key}")
    private String secret;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDTO findById(Long id) throws UserException {
        UserDTO userDTO;
        try{
           User user = this.repository.findById(
                   id).orElseThrow(() -> new UserException("Usuario no encontrado"));
           userDTO = this.parserUserModelToDto(user);
        }catch (Exception ex){
            logger.error(ex);
            throw new UserException(ex.getMessage());
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() throws UserException {
        List<UserDTO> userDTOList = new ArrayList<>();
        try{
            List<User> users = this.repository.findAll();
            for (User user: users) {
                userDTOList.add(this.parserUserModelToDto(user));
            }
        }catch (Exception ex){
            logger.error(ex);
            throw new UserException(ex.getMessage());
        }
        return userDTOList;
    }

    @Transactional
    @Override
    public ResponseDTO create(UserDTO userDTO) throws UserException {
        ResponseDTO responseDTO;
        try{

            List<User> userValid = this.repository.findByEmail(userDTO.getEmail());

            if (userValid.size() > 0){
                throw new UserException("El correo ya registrado.");
            }

            if (this.validateRegex(userDTO.getEmail(), REGEX_EMAIL)){
                throw new UserException("Formato de correo incorrecto.");
            }

            if (this.validateRegex(userDTO.getPassword(), REGEX_PASSWORD)){
                throw new UserException("Formato de contraseña incorrecto.");
            }

            User userModel = new User();
            this.parseUserDtoToUserModel(userModel, userDTO);
            User userReturn = this.repository.save(userModel);
            responseDTO = this.parserUserModelToResponseDto(userReturn);
        }catch (Exception ex){
            logger.error(ex);
            throw new UserException(ex.getMessage());
        }
        return responseDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) throws UserException {
        UserDTO userDTOReturn;
        try{
            User userModel = this.repository.findById(userDTO.getId())
                    .orElseThrow(() -> new UserException("Usuario no encontrado"));

            if (this.validateRegex(userDTO.getEmail(), REGEX_EMAIL)){
                throw new UserException("Formato de correo incorrecto.");
            }

            if (this.validateRegex(userDTO.getPassword(), REGEX_PASSWORD)){
                throw new UserException("Formato de contraseña incorrecto.");
            }

            this.parseUserDtoToUserModel(userModel, userDTO);
            User userData =this.repository.save(userModel);

            userDTOReturn = parserUserModelToDto(userData);
        }catch (Exception ex){
            logger.error(ex);
            throw new UserException(ex.getMessage());
        }
        return userDTOReturn;
    }

    @Override
    public UserDTO delete(Long id) throws UserException {
        UserDTO userDTO = new UserDTO();
        try{
            this.repository.deleteById(id);

            this.repository.findById(id)
                    .orElseThrow(() -> new UserException("Usuario Eliminado"));
        }catch (Exception ex){
            logger.error(ex);
            throw new UserException(ex.getMessage());
        }
        return userDTO;
    }

    private String generateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private UserDTO parserUserModelToDto(User user){
        UserDTO ret = new UserDTO();

        ret.setId(user.getId());
        ret.setEmail(user.getEmail());
        ret.setName(user.getName());
        ret.setPassword(user.getPassword());

        List<PhoneDTO> phonesDTO = new ArrayList<>();
        PhoneDTO phoneDTO;
        for (Phone phone: user.getPhones()) {
            phoneDTO = new PhoneDTO();
            phoneDTO.setId(phone.getId());
            phoneDTO.setCity_code(phone.getCity_code());
            phoneDTO.setCountry_code(phone.getCountry_code());
            phoneDTO.setNumber(phone.getNumber());
            phonesDTO.add(phoneDTO);
        }
        if (phonesDTO.size() > 0){
            ret.setPhones(phonesDTO);
        }

        return ret;
    }

    private ResponseDTO parserUserModelToResponseDto(User user){
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setActive(user.getActive());
        responseDTO.setCreated(user.getCreated());
        responseDTO.setToken(user.getToken());
        responseDTO.setModified(user.getModified());
        responseDTO.setId(user.getId());
        responseDTO.setLastLogin(user.getLastLogin());
        return responseDTO;
    }

    private void parseUserDtoToUserModel(User userModel, UserDTO userDTO){
        userModel.setId(userDTO.getId());
        userModel.setCreated(new Date());
        userModel.setLastLogin(new Date());
        userModel.setName(userDTO.getName());
        Map<String, Object> claims = new HashMap<>();
        userModel.setToken(this.generateToken(claims, userDTO.getName()));
        userModel.setActive(true);
        userModel.setPassword(userDTO.getPassword());
        userModel.setEmail(userDTO.getEmail());

        List<Phone> phoneList = new ArrayList<>();
        Phone phoneData;
        for (PhoneDTO p: userDTO.getPhones()) {
            phoneData = new Phone();
            phoneData.setNumber(p.getNumber());
            phoneData.setCity_code(p.getCity_code());
            phoneData.setCountry_code(p.getCountry_code());
            phoneList.add(phoneData);
        }
        if (phoneList.size() > 0){
            userModel.setPhones(phoneList);
        }
    }

    public static boolean validateRegex(String input, String regexPattern) {
        return !Pattern.matches(regexPattern, input);
    }
}
