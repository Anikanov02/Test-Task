package com.test.task.frontend.client.service;

import com.test.task.frontend.client.domain.dto.ContractDto;
import com.test.task.frontend.client.domain.dto.LoginDto;
import com.test.task.frontend.client.domain.dto.UserDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("api/v1/user/")
public interface UserRequestService extends RestService {
    @POST
    @Path("/login")
    void login(LoginDto loginDto,
               MethodCallback<UserDto> callback);

    @POST
    @Path("/new")
    void newUser(UserDto userDto,
                 MethodCallback<UserDto> callback);

    @GET
    @Path("/data/{userId}")
    void getUserData(@PathParam("userId") long userId,
                     MethodCallback<UserDto> callback);

    @GET
    @Path("/{userId}/contracts")
    void getContracts(@PathParam("userId") long userId,
                      MethodCallback<List<ContractDto>> callback);

    @POST
    @Path("/edit/{userId}")
    void editUserData(@PathParam("userId") long userId, UserDto userDto,
                      MethodCallback<UserDto> callback);

    @DELETE
    @Path("/delete/{userId}")
    void deleteUser(@PathParam("userId") long userId,
                    MethodCallback<Void> callback);
}
