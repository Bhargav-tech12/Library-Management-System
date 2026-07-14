package com.librarymanagement.model;

import java.util.Objects;

public class Librarian {
    private String librarianId;
    private String name;
    private String email;
    private String phone;

    public Librarian() {
    }

    public Librarian(String librarianId, String name, String email, String phone) {
        this.librarianId = librarianId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(String librarianId) {
        this.librarianId = librarianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Librarian)) {
            return false;
        }
        Librarian librarian = (Librarian) object;
        return Objects.equals(librarianId, librarian.librarianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(librarianId);
    }

    @Override
    public String toString() {
        return "Librarian{"
                + "librarianId='" + librarianId + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
