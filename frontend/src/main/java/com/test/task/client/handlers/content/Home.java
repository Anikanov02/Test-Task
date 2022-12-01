package com.test.task.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.test.task.client.GlobalSessionInfo;
import com.test.task.client.domain.dto.ContractDto;
import com.test.task.client.domain.dto.UserDto;
import com.test.task.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

public class Home extends Page {

    private VerticalPanel accountContentPanel;
    private VerticalPanel contractsContentPanel;
    private UserRequestService userService;

    public Home() {
        userService = GWT.create(UserRequestService.class);

        TabPanel panel = new TabPanel();
        accountContentPanel = new VerticalPanel();
        setupAccountTab(accountContentPanel);
        contractsContentPanel = new VerticalPanel();
        setupContractsTab(contractsContentPanel);

        panel.add(accountContentPanel, "Account");
        panel.add(contractsContentPanel, "Contracts");
        initWidget(panel);
    }

    private void setupAccountTab(CellPanel panel) {
        final UserDataComposite userData = new UserDataComposite();
        userData.loadUserData(GlobalSessionInfo.userDto);
        final Button update = new Button("Update");
        panel.add(userData);
        panel.add(update);

        update.addClickHandler(event -> {
            userData.refresh();
            if (userData.validated()) {
                UserDto userDto = new UserDto(userData.getFirstNameBox().getText().trim(),
                        userData.getLastNameBox().getText().trim(),
                        userData.getFathersNameBox().getText().trim(),
                        userData.getLoginBox().getText().trim(),
                        userData.getPasswordTextBox().getText().trim(),
                        userData.getConfirmPasswordTextBox().getText(),
                        userData.getPhoneNumberBox().getText().trim());
                userService.editUserData(GlobalSessionInfo.userDto.getId(), userDto, new MethodCallback<UserDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        userData.message("updating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, UserDto userDto) {
                        GlobalSessionInfo.userDto = userDto;
                    }
                });
            }
        });
    }

    private void setupContractsTab(CellPanel panel) {
        final ContractsMenuComposite contractsManu = new ContractsMenuComposite();

        final List<ContractDto> contracts = new ArrayList<>();
        userService.getContracts(GlobalSessionInfo.userDto.getId(), new MethodCallback<List<ContractDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                contractsManu.getInfoLabel().setText("Some problem occurred while loading contracts data");
            }

            @Override
            public void onSuccess(Method method, List<ContractDto> data) {
                contracts.addAll(data);
            }
        });
        contractsManu.loadContracts(contracts);

        panel.add(contractsManu);
    }

    @Override
    public String getTitle() {
        return "Home Page";
    }
}
