package edu.ynjgy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer id;
    private String title;
    private String description;
    private String organizer_id;
    private String event_time;
    private String location;
    private String status;
}
