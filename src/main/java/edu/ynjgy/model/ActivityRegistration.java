package edu.ynjgy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRegistration {
    private Integer id;
    private Integer activity_id;
    private Integer student_id;
    private Integer register_time;
}
