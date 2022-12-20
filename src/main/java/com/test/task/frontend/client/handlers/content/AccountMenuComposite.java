package com.test.task.frontend.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.client.GlobalSessionInfo;
import com.test.task.frontend.client.domain.dto.UserDto;
import com.test.task.frontend.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class AccountMenuComposite extends Composite {
    private final UserDataComposite userData;
    private final Button update;
    private final Button delete;
    //    @UiField
//    private final Label passwordLabel;
//    @UiField
//    private final TextBox password;
    private final UserRequestService userService;

    public AccountMenuComposite() {
        userService = GWT.create(UserRequestService.class);

        final VerticalPanel panel = new VerticalPanel();
        userData = new UserDataComposite();
        userData.getPasswordLabel().setText("New Password:");
        userData.loadUserData(GlobalSessionInfo.userDto);
//        passwordLabel = new Label("Enter your current password:");
//        password = new TextBox();
        update = new Button("Update");
        delete = new Button("Delete user");
        panel.add(userData);
//        panel.add(passwordLabel);
//        panel.add(password);
        panel.add(update);
        panel.add(delete);
        initWidget(panel);

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
            if (Window.confirm("Confirm deleting user? cannot be undone")) {
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
}
