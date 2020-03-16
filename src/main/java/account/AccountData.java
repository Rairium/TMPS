package account;

import locations.Location;

public class AccountData {
    private final int id;
    private final String email;
    private final Location location;
    private final String name;
    private final String surname;
    private final String phone;

    private AccountData(AccountBuilder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.location = builder.location;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phone = builder.phone;
    }

    public static class AccountBuilder {
        private int id;
        private String email;
        private Location location;
        private String name;
        private String surname;
        private String phone;

        public AccountBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public AccountBuilder location(final Location location) {
            this.location = location;
            return this;
        }

        public AccountBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public AccountBuilder surname(final String surname) {
            this.surname = surname;
            return this;
        }

        public AccountBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        public AccountBuilder id(final int id) {
            this.id = id;
            return this;
        }
        public AccountData build(){
            return new AccountData(this);
        }
    }
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }
}
