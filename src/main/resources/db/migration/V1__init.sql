create TABLE "group" (
  uuid UUID NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_group PRIMARY KEY (uuid)
);

create TABLE group_students (
  group_uuid UUID NOT NULL,
   students_uuid UUID NOT NULL,
   CONSTRAINT pk_group_students PRIMARY KEY (group_uuid, students_uuid)
);

create TABLE student (
  uuid UUID NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_student PRIMARY KEY (uuid)
);

alter table group_students add CONSTRAINT uc_group_students_group_uuid UNIQUE (group_uuid);

alter table "group" add CONSTRAINT uc_group_uuid UNIQUE (uuid);

alter table student add CONSTRAINT uc_student_uuid UNIQUE (uuid);

alter table group_students add CONSTRAINT fk_grostu_on_group FOREIGN KEY (group_uuid) REFERENCES "group" (uuid);

alter table group_students add CONSTRAINT fk_grostu_on_student FOREIGN KEY (students_uuid) REFERENCES student (uuid);