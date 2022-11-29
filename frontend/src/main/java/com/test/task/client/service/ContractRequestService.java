package com.test.task.client.service;

import com.test.task.client.domain.dto.ContractDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

@Path("api/v1/user")
public interface ContractRequestService extends RestService {
    @POST
    @Path("new") void newContract(ContractDto userDto,
                                  MethodCallback<ContractDto> callback);
    @GET
    @Path("data/{contractId}") void getContractData(@PathParam("contractId") Long contractId,
                                            MethodCallback<ContractDto> callback);
    @POST
    @Path("edit/{contractId}") void editContractData(@PathParam("contractId") Long contractId, ContractDto userDto,
                                             MethodCallback<ContractDto> callback);
    @DELETE
    @Path("delete/{contractId}") void deleteContract(@PathParam("contractId") Long contractId,
                                             MethodCallback<Void> callback);
    @POST
    @Path("archive/{contractId}") void archiveContract(@PathParam("contractId") Long contractId,
                                                 MethodCallback<ContractDto> callback);
}
