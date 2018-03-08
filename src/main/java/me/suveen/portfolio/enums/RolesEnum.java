package me.suveen.portfolio.enums;

/**
 * defines the possible roles.
 */
public enum RolesEnum {

    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final int id;
    private final String roleName;

    RolesEnum(int id, String roleName){
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {

        return id;
    }

    public String getRoleName() {

        return roleName;
    }
}
