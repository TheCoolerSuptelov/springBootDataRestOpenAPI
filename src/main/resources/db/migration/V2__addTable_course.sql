create sequence  IF NOT EXISTS hibernate_sequence START with 1 INCREMENT BY 1;

create TABLE course (
  id UUID NOT NULL,
   title VARCHAR(255),
   amount_of_hours INTEGER,
   CONSTRAINT pk_course PRIMARY KEY (id)
);

alter table student add course_id UUID;

alter table student add date_of_birthday date;

alter table student add CONSTRAINT FK_STUDENT_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);