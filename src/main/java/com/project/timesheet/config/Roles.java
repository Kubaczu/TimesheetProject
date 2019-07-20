package com.project.timesheet.config;

public enum Roles {
    ADMIN("ADMIN", "ROLE_ADMIN"),
    USER("USER", "ROLE_USER");

    private String roleName;
    private String authorityName;

    Roles(String roleName, String authorityName) {
        this.roleName = roleName;
        this.authorityName = authorityName;
    }


    public String getRoleName() {
        return roleName;
    }

    public String getAuthorityName() {
        return authorityName;
    }
}