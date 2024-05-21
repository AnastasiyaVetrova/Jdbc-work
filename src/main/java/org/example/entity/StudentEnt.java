package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentEnt {
  private int id;
  private String name;
  private AddressEnt address;
  private String facultyId;
  private List<HobbyEnt> hobbyEnts;
  private List<CourseEnt> courseEnts;
}
