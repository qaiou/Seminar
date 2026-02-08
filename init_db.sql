-- SQLite Database Schema for Seminar Management System
-- Converted from MySQL

-- Drop existing tables if they exist
DROP TABLE IF EXISTS assignment;
DROP TABLE IF EXISTS evaluation;
DROP TABLE IF EXISTS submission;
DROP TABLE IF EXISTS session;
DROP TABLE IF EXISTS evaluators;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS coordinators;
DROP TABLE IF EXISTS users;

-- Users table
CREATE TABLE users (
  id VARCHAR(12) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  password VARCHAR(8) NOT NULL,
  role VARCHAR(12) NOT NULL
);

-- Coordinators table
CREATE TABLE coordinators (
  id VARCHAR(12) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  FOREIGN KEY (id) REFERENCES users(id)
);

-- Evaluators table
CREATE TABLE evaluators (
  id VARCHAR(12) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  FOREIGN KEY (id) REFERENCES users(id)
);

-- Students table
CREATE TABLE students (
  id VARCHAR(12) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  FOREIGN KEY (id) REFERENCES users(id)
);

-- Session table
CREATE TABLE session (
  sessionID INTEGER PRIMARY KEY AUTOINCREMENT,
  session_date DATE NOT NULL,
  venue VARCHAR(100) NOT NULL,
  session_type TEXT CHECK(session_type IN ('Oral', 'Poster')) NOT NULL,
  status VARCHAR(50) NOT NULL DEFAULT 'Scheduled'
);

-- Submission table
CREATE TABLE submission (
  submissionID INTEGER PRIMARY KEY AUTOINCREMENT,
  studentID VARCHAR(12) NOT NULL,
  title VARCHAR(255) NOT NULL,
  abstract TEXT NOT NULL,
  supervisor VARCHAR(100) NOT NULL,
  type TEXT CHECK(type IN ('Oral', 'Poster')) NOT NULL,
  file_path VARCHAR(255) NOT NULL,
  status VARCHAR(50) NOT NULL DEFAULT 'Submitted',
  FOREIGN KEY (studentID) REFERENCES users(id)
);

-- Assignment table
CREATE TABLE assignment (
  assignID INTEGER PRIMARY KEY AUTOINCREMENT,
  sessionID INTEGER NOT NULL,
  submissionID INTEGER NOT NULL,
  evaluatorID VARCHAR(12) NOT NULL,
  status VARCHAR(50) NOT NULL DEFAULT 'Assigned',
  FOREIGN KEY (evaluatorID) REFERENCES users(id),
  FOREIGN KEY (sessionID) REFERENCES session(sessionID),
  FOREIGN KEY (submissionID) REFERENCES submission(submissionID)
);

-- Evaluation table
CREATE TABLE evaluation (
  evaluationID INTEGER PRIMARY KEY AUTOINCREMENT,
  assignmentID INTEGER NOT NULL,
  problem_clarity INTEGER NOT NULL,
  methodology INTEGER NOT NULL,
  results INTEGER NOT NULL,
  presentation INTEGER NOT NULL,
  comments TEXT NOT NULL,
  total_score INTEGER NOT NULL,
  FOREIGN KEY (assignmentID) REFERENCES assignment(assignID)
);

-- Insert sample data
INSERT INTO users (id, name, password, role) VALUES
('coordinator1', 'Ali Alfendi Bin Affendi', '1234', 'Coordinator'),
('coordinator2', 'Nelly Binti Sully', '1234', 'Coordinator'),
('evaluator1', 'Joelle Tan Su Ai', '1234', 'Evaluator'),
('evaluator2', 'Sasita A/P Sajeshwaran', '1234', 'Evaluator'),
('student1', 'Qaisara Hannah Binti Kamarul Ariffin', '1234', 'Student'),
('student2', 'Khamis Bin Rabu', '1234', 'Student'),
('student3', 'Tujuh Bin Enam', '1234', 'Student');


INSERT INTO students (id, name) VALUES
('student1', 'Qaisara Hannah Binti Kamarul Ariffin'),
('student2', 'Khamis Bin Rabu'),
('student3', 'Tujuh Bin Enam');

INSERT INTO evaluators (id, name) VALUES
('evaluator1', 'Joelle Tan Su Ai'),
('evaluator2', 'Sasita A/P Sajeshwaran');

INSERT INTO coordinators (id, name) VALUES
('coordinator1', 'Ali Alfendi Bin Affendi'),
('coordinator2', 'Nelly Binti Sully');

INSERT INTO session (session_date, venue, session_type, status) VALUES
('2026-02-08', 'dtc', 'Oral', 'Scheduled'),
('2026-02-08', 'dtc', 'Oral', 'Scheduled');

INSERT INTO submission (studentID, title, abstract, supervisor, type, file_path, status) VALUES
('student2', 'THIS IS A TITLE', 'ABSTRACT OF REASEARCH2', 'Mrs Naili', 'Oral', '/desktop', 'Submitted');
