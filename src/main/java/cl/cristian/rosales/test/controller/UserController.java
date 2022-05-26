package cl.cristian.rosales.test.controller;

import cl.cristian.rosales.test.dto.ResponseDTO;
import cl.cristian.rosales.test.dto.UserDTO;
import cl.cristian.rosales.test.exception.UserException;
import cl.cristian.rosales.test.models.User;
import cl.cristian.rosales.test.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Long id) throws UserException  {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.findById(id));
    }
    @RequestMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAll()  throws UserException {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.findAll());
    }

    @RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> create(@RequestBody UserDTO userDTO) throws UserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.create(userDTO));
    }

    @RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) throws UserException  {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.update(userDTO));
    }

    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> delete(@PathVariable("id") Long id) throws UserException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.userService.delete(id));
    }

}
