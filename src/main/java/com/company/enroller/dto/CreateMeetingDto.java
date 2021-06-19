package com.company.enroller.dto;

public class CreateMeetingDto {

    private final String title;
    private final String description;
    private final String date;

    public CreateMeetingDto(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
