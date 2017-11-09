package com.kyle.mvp.bean;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/8
 */
public class GitBean {

    /**
     * message : Requires authentication
     * documentation_url : https://developer.github.com/v3/users/#update-the-authenticated-user
     */

    private String message;
    private String documentation_url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }

    @Override
    public String toString() {
        return "GitBean{" +
                "message='" + message + '\'' +
                ", documentation_url='" + documentation_url + '\'' +
                '}';
    }
}
