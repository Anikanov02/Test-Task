package com.test.task.frontend.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.client.GlobalSessionInfo;
import com.test.task.frontend.client.domain.dto.ContractDto;
import com.test.task.frontend.client.domain.dto.UserDto;
import com.test.task.frontend.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Iterator;
import java.util.List;

public class Home extends Page {

    private VerticalPanel accountContentPanel;
    private VerticalPanel contractsContentPanel;
    private UserRequestService userService;

    public Home() {
        userService = GWT.create(UserRequestService.class);

        TabPanel panel = new TabPanel();
        accountContentPanel = new VerticalPanel();
        contractsContentPanel = new VerticalPanel();
        panel.add(accountContentPanel, "Account");
        panel.add(contractsContentPanel, "Contracts");
        initWidget(panel);

        panel.addSelectionHandler(event -> {
            if (panel.getTabBar().getSelectedTab() == 0) {
                setupAccountTab(accountContentPanel);
            } else if (panel.getTabBar().getSelectedTab() == 1) {
                setupContractsTab(contractsContentPanel);
            }
        });
    }

    private void setupAccountTab(CellPanel panel) {
        clearPanel(panel);

        final UserDataComposite userData = new UserDataComposite();
        userData.loadUserData(GlobalSessionInfo.userDto);
        final Button update = new Button("Update");
        final Button delete = new Button("Delete user");
        panel.add(userData);
        panel.add(update);
        panel.add(delete);

        update.addClickHandler(event -> {
            userData.refresh();
            if (userData.validated()) {
                UserDto userDto = new UserDto(userData.getFirstNameBox().getText().trim(),
                        userData.getLastNameBox().getText().trim(),
                        userData.getFathersNameBox().getText().trim(),
                        userData.getLoginBox().getText().trim(),
                        userData.getPasswordTextBox().getText().trim(),
                        userData.getDateOfBirthBox().getText(),
                        userData.getPhoneNumberBox().getText().trim());
                userService.editUserData(GlobalSessionInfo.userDto.getId(), userDto, new MethodCallback<UserDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        userData.message("updating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, UserDto userDto) {
                        userData.message("successfully updated user data");
                        GlobalSessionInfo.userDto = userDto;
                    }
                });
            }
        });

        delete.addClickHandler(event -> {
            if(Window.confirm("Confirm deleting user? cannot be undone")) {
                userService.deleteUser(GlobalSessionInfo.userDto.getId(), new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        userData.message("failed deleting user");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        userData.message("successfully deleted user");
                        GlobalSessionInfo.userDto = null;
                        PageLoader.go(new Login());
                    }
                });
            }
        });
    }

    private void setupContractsTab(CellPanel panel) {
        clearPanel(panel);

        final ContractsMenuComposite contractsManu = new ContractsMenuComposite();
        panel.add(contractsManu);

        userService.getContracts(GlobalSessionInfo.userDto.getId(), new MethodCallback<List<ContractDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                contractsManu.getInfoLabel().setText("Some problem occurred while loading contracts data: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<ContractDto> data) {
                Window.alert(data.size() + " contracts " + data.get(0).getId());
                contractsManu.loadContracts(data);
            }
        });
    }

    private void clearPanel(CellPanel panel) {
        Iterator<Widget> itr = panel.iterator();
        while(itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }

    @Override
    public String getTitle() {
        return "Home Page";
    }
}
