package com.baway.guo.myapp.reg.regentity;

public class RegEntity {
    private String message;
    private String status;

    @Override
    public String toString() {
        return "RegEntity{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RegEntity(String message, String status) {

        this.message = message;
        this.status = status;
    }
}
