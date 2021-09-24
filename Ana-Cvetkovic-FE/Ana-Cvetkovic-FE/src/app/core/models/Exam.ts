import { ExamPeriod } from "./ExamPeriod";
import { Professor } from "./professor.model";
import { Subject } from "./subject.model";

export interface Exam{
  id: number;
	examPeriod: ExamPeriod;
	subject: Subject;
  professor: Professor;
	examDate: Date;
}
