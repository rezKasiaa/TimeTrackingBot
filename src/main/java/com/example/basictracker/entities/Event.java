package com.example.basictracker.entities;

import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.EventHelper;
import com.example.basictracker.utils.Utils;
import lombok.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@ToString
public class Event {
    private String id;
    private String userId;
    private String activityId;
    private LocalDateTime timeStamp;
}
