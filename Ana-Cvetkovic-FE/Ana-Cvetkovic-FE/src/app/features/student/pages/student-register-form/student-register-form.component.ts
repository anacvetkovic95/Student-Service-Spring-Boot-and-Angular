import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core/models/student.model';
import { HttpStudentService } from 'src/app/service/http-student.service';
import { ToastService } from 'src/app/service/toast.service';
import { Subject as SubjectObservable } from 'rxjs';
import { Exam } from 'src/app/core/models/Exam';
import { HttpExamService } from 'src/app/service/http-exam.service';

@Component({
  selector: 'app-student-register-form',
  templateUrl: './student-register-form.component.html',
  styleUrls: ['./student-register-form.component.css']
})
export class StudentRegisterFormComponent implements OnInit {

  student: Student;
  exams: Exam[];
  activeExams: Exam[]=[];
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private httpStudent:HttpStudentService,
    private httpExam: HttpExamService,
    private fb: FormBuilder,
    private route:ActivatedRoute,
    private router:Router,
    private toastService: ToastService
  ) { }

  ngOnInit(): void {
    this.loadStudent();
    this.loadExams();
  }

  loadStudent(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.httpStudent.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( student => {
      this.student=student;
    });
  }

  loadExams(){
    this.httpExam.getAll()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( exams => {
      this.exams=exams;
    });
  }



}
