package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


public class StudentDTO {

  @NotBlank(message = "Name is empty. Can not save this data.")
  @NotEmpty(message = "Error. Empty name")
  private String name;

  private Date dateOfBirthday;

  private String course;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateOfBirthday() {
    return dateOfBirthday;
  }

  public void setDateOfBirthday(Date dateOfBirthday) {
    this.dateOfBirthday = dateOfBirthday;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }
}
