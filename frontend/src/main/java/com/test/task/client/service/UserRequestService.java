package com.test.task.client.service;

import com.test.task.client.domain.dto.ContractDto;
import com.test.task.client.domain.dto.LoginDto;
import com.test.task.client.domain.dto.UserDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("api/v1/contract")
public interface UserRequestService extends RestService {
    @POST
    @Path("login") void login(LoginDto loginDto,
                             MethodCallback<UserDto> callback);
    @POST
    @Path("new") void newUser(UserDto userDto,
                             MethodCallback<UserDto> callback);
    @GET
    @Path("data/{userId}") void getUserData(@PathParam("userId") Long userId,
                             MethodCallback<UserDto> callback);
    @GET
    @Path("{userId}/contracts") void getContracts(@PathParam("userId") Long userId,
                                            MethodCallback<List<ContractDto>> callback);
    @POST
    @Path("edit/{userId}") void editUserData(@PathParam("userId") Long userId, UserDto userDto,
                             MethodCallback<UserDto> callback);
    @DELETE
    @Path("delete/{userId}") void deleteUser(@PathParam("userId") Long userId,
                             MethodCallback<Void> callback);
}
