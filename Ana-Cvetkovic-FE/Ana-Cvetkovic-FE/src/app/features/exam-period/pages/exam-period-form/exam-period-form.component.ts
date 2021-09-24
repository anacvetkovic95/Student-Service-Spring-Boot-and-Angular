import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExamPeriod } from 'src/app/core/models/ExamPeriod';
import { Subject as SubjectObservable} from 'rxjs';
import { HttpExamPeriodService } from 'src/app/service/http-exam-period.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from 'src/app/service/toast.service';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-exam-period-form',
  templateUrl: './exam-period-form.component.html',
  styleUrls: ['./exam-period-form.component.css']
})
export class ExamPeriodFormComponent implements OnInit {

  examPeriodForm:FormGroup;
  addedExamPeriod:ExamPeriod;
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private httpExamPeriod:HttpExamPeriodService,
    private fb: FormBuilder,
    private route:ActivatedRoute,
    private router:Router,
    private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExamPeriod(id);
    } else {
      this.buildForm();
    }
  }
  buildForm(examPeriod?:ExamPeriod){
    this.examPeriodForm = this.fb.group(
      {
        name:[examPeriod? examPeriod.name:null ,[Validators.required, Validators.minLength(3)]],
        startPeriod:[examPeriod? examPeriod.startPeriod:null],//da bude manji od endPeriod
        endPeriod:[examPeriod? examPeriod.endPeriod:null,Validators.required],//da bude veci od startPeriod
        active:[examPeriod? examPeriod.active:null,[Validators.required]],
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
      subject => {
        this.toastService.show('Examp period saved!', {header:'Saving exam period', classname: 'bg-success text-light'});
        this.router.navigate(['/exam-period/exam-period-list']);
        this.addedExamPeriod=this.examPeriodForm.value;
      },
      error => {
        this.toastService.show('Examp period is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving exam period', classname: 'bg-danger text-light'});
      }

    );
  }
  saveExamPeriod() {
    if (this.edit) {
      return this.httpExamPeriod.updateExamPeriod(this.examPeriodForm.value)
    } else {
      return this.httpExamPeriod.insertExamPeriod(this.examPeriodForm.value)
    }
  }

  loadExamPeriod(id:number){
    this.httpExamPeriod.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( examPeriod => {
      console.log(examPeriod);
      this.buildForm(examPeriod);
    });
  }
  hasErrors(componentName: string, errorCode: string) {
    return  (this.examPeriodForm.get(componentName).dirty || this.examPeriodForm.get(componentName).touched) && this.examPeriodForm.get(componentName).hasError(errorCode);
  }

}
