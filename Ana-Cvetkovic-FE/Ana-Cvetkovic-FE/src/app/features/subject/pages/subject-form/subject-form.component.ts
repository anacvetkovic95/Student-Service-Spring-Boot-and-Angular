import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Semester, Subject } from 'src/app/core';
import { HttpSubjectService } from 'src/app/service/http-subject.service';
import { ToastService } from 'src/app/service/toast.service';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-subject-form',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.css']
})
export class SubjectFormComponent implements OnInit, OnDestroy {

  subjectForm:FormGroup;
  semester = Semester;
  addedSubject:Subject;
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private httpSubjectService:HttpSubjectService,
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
      this.loadSubject(id);
    } else {
      this.buildForm();
    }
  }

  loadSubject(id:number){
    this.httpSubjectService.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( subject => {
      this.addedSubject=subject;
      this.buildForm(subject);
    });
  }

  buildForm(subject?:Subject){
    this.subjectForm = this.fb.group(
      {
        name: [ subject? subject.name:null , [ Validators.required, Validators.minLength(3), Validators.maxLength(30) ] ],
        description: [ subject? subject.description: null, [ Validators.maxLength(200) ] ],
        noOfESPB: [ subject? subject.noOfESPB: null, [ Validators.required, Validators.maxLength(1) ] ],
        yearOfStudy: [ subject? subject.yearOfStudy: null, [ Validators.required, Validators.maxLength(1) ] ],
        semester: [ subject? subject.semester:null, [ Validators.required ] ],
        id: [ +this.route.snapshot.paramMap.get('id') ]
      }
    );
  }

  onSubmit() {
    this.saveSubject()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      subject => {
        this.toastService.show('Subject saved!', { header:'Saving subject', classname: 'bg-success text-light' });
        this.router.navigate(['/subject/subject-list']);
        this.addedSubject=this.subjectForm.value;
      },
      error => {
        this.toastService.show('Subject is not saved: ' + ( typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving Subject', classname: 'bg-danger text-light'});
      }

    );
  }

  saveSubject() {
    if (this.edit) {
      return this.httpSubjectService.updateSubject(this.subjectForm.value)
    } else {
      return this.httpSubjectService.insertSubject(this.subjectForm.value)
    }
  }

  getEnumKeys() {
    return Object.keys( Semester).map( key=> this.semester[key] );
  }

  hasErrors( componentName: string, errorCode: string ) {
    return  ( this.subjectForm.get(componentName).dirty || this.subjectForm.get(componentName).touched) && this.subjectForm.get(componentName).hasError(errorCode );
  }

}
