package konkuk.samchuck.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    private String userid;

    @NotNull
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
