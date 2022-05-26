package cl.cristian.rosales.test.services;

import cl.cristian.rosales.test.dto.ResponseDTO;
import cl.cristian.rosales.test.dto.UserDTO;
import cl.cristian.rosales.test.exception.UserException;

import java.util.List;

public interface IUserService {

    UserDTO findById(Long id) throws UserException;
    List<UserDTO> findAll() throws UserException;
    ResponseDTO create(UserDTO user)  throws UserException;
    UserDTO update(UserDTO user) throws UserException;
    UserDTO delete(Long id)  throws UserException;
}
