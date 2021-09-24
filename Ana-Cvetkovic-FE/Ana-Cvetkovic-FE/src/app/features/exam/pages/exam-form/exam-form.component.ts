import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'src/app/core/models';
import { Exam } from 'src/app/core/models/Exam';
import { ExamPeriod } from 'src/app/core/models/ExamPeriod';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpExamPeriodService } from 'src/app/service/http-exam-period.service';
import { HttpExamService } from 'src/app/service/http-exam.service';
import { HttpSubjectService } from 'src/app/service/http-subject.service';
import { ToastService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-exam-form',
  templateUrl: './exam-form.component.html',
  styleUrls: ['./exam-form.component.css']
})
export class ExamFormComponent implements OnInit {

  examForm:FormGroup;
  addedExam:Exam;
  examPerods: ExamPeriod[];
  subjects: Subject[];
  professors: Professor[];
  selectedSubject: Subject;
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private httpExam:HttpExamService,
    private httpExamPeriod: HttpExamPeriodService,
    private httpSubject: HttpSubjectService,
    private fb: FormBuilder,
    private route:ActivatedRoute,
    private router:Router,
    private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
    this.loadExamPeriods();
    this.loadSubjects();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExam(id);
    } else {
      this.buildForm();
    }
  }

  loadExam(id:number){
    this.httpExam.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( exam => {
      console.log(exam);
      this.buildForm(exam);
    });
  }

  loadSubjects(){
    this.httpSubject.getAll().subscribe(
      response => {
        this.subjects=response;
      }
    );
  }

  loadExamPeriods(){
    this.httpExamPeriod.getAll().subscribe(
      response => {
        this.examPerods=response;
      }
    );
  }

  buildForm(exam?:Exam){
    this.examForm = this.fb.group(
      {
        examDate:[exam? exam.examDate:null,[Validators.required]],
        examPeriod:[exam? exam.examPeriod:null,[Validators.required]],
        subject:[exam? exam.subject:null,[Validators.required]],
        professor:[exam? exam.professor:null,[Validators.required]],
        id:[+this.route.snapshot.paramMap.get('id')]
      }
    );
  }
  onSubmit() {
    this.saveExamPeriod()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      exam => {
        this.toastService.show('Examp  saved!', {header:'Saving exam ', classname: 'bg-success text-light'});
        this.router.navigate(['/exam/exam-list']);
        this.addedExam=this.examForm.value;
      },
      error => {
        this.toastService.show('Examp is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving exam ', classname: 'bg-danger text-light'});
      }

    );
  }
  saveExamPeriod() {
    if (this.edit) {
      return this.httpExam.updateExam(this.examForm.value)
    } else {
      return this.httpExam.insertExam(this.examForm.value)
    }
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.examForm.get(componentName).dirty || this.examForm.get(componentName).touched) && this.examForm.get(componentName).hasError(errorCode);
  }

  onSelectSubject(event: any){
    console.log(this.selectedSubject);
    this.professors = this.selectedSubject.engagedProfessors;
    console.log(this.professors);
  }

}
