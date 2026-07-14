package com.librarymanagement.model;

import java.time.LocalDate;
import java.util.Objects;

public class BorrowRecord {
    private String borrowId;
    private String bookId;
    private String studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    private BorrowStatus borrowStatus;

    public BorrowRecord() {
    }

    public BorrowRecord(String borrowId, String bookId, String studentId, LocalDate issueDate, LocalDate dueDate,
                        LocalDate returnDate, double fine, BorrowStatus borrowStatus) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BorrowRecord)) {
            return false;
        }
        BorrowRecord that = (BorrowRecord) object;
        return Objects.equals(borrowId, that.borrowId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowId);
    }

    @Override
    public String toString() {
        return "BorrowRecord{"
                + "borrowId='" + borrowId + '\''
                + ", bookId='" + bookId + '\''
                + ", studentId='" + studentId + '\''
                + ", issueDate=" + issueDate
                + ", dueDate=" + dueDate
                + ", returnDate=" + returnDate
                + ", fine=" + fine
                + ", borrowStatus=" + borrowStatus
                + '}';
    }
}
