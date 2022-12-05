package com.test.task.frontend.client.service;

import com.test.task.frontend.client.domain.dto.ContractDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

@Path("api/v1/contract/")
public interface ContractRequestService extends RestService {
    @POST
    @Path("/new")
    void newContract(ContractDto userDto,
                     MethodCallback<ContractDto> callback);

    @GET
    @Path("/data/{contractId}")
    void getContractData(@PathParam("contractId") long contractId,
                         MethodCallback<ContractDto> callback);

    @POST
    @Path("/edit/{contractId}")
    void editContractData(@PathParam("contractId") long contractId, ContractDto userDto,
                          MethodCallback<ContractDto> callback);

    @DELETE
    @Path("/delete/{contractId}")
    void deleteContract(@PathParam("contractId") long contractId,
                        MethodCallback<Void> callback);

    @POST
    @Path("/archive/{contractId}")
    void archiveContract(@PathParam("contractId") long contractId,
                         MethodCallback<ContractDto> callback);
}
