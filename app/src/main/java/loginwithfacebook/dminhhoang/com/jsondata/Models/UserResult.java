package loginwithfacebook.dminhhoang.com.jsondata.Models;

import java.util.List;

/**
 * Created by dminh on 1/29/2018.
 */

public class UserResult {
    private String status;
    private List<Accounts> users;

    public String getStatus() {
        return status;
    }

    public List<Accounts> getUsers() {
        return users;
    }
}
