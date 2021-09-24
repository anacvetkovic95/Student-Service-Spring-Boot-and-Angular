import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { City, Student } from 'src/app/core/models/student.model';
import { HttpCityService } from 'src/app/service/http-city.service';
import { HttpStudentService } from 'src/app/service/http-student.service';
import { ToastService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent implements OnInit, OnDestroy {

  studentForm:FormGroup;
  cities: City[];
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private httpStudent:HttpStudentService,
    private httpCity: HttpCityService,
    private fb: FormBuilder,
    private route:ActivatedRoute,
    private router:Router,
    private toastService: ToastService) { }

  ngOnInit(): void {
    this.loadCities();
    this.prepareData();
  }

  ngOnDestroy() {
    this.destroy$.next(true);
  }

  loadCities(){
    this.httpCity.getAll().subscribe(
      response => {
        this.cities=response;
      }
    );
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadStudent(id);
    } else {
      this.buildForm();
    }
  }

  loadStudent(id:number){
    this.httpStudent.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( student => {
      this.buildForm(student);
    });
  }

  buildForm(student?:Student){
    this.studentForm = this.fb.group(
      {
        indexNumber: [ student? student.indexNumber: null, [ Validators.required, Validators.minLength(4), Validators.maxLength(4) ] ],
        indexYear: [ student? student.indexYear: null, [ Validators.required, Validators.min(2000),  Validators.max(2100) ] ],
        firstName: [ student? student.firstName: null, [ Validators.required, Validators.minLength(3), Validators.maxLength(30) ] ],
        lastName: [ student? student.lastName: null, [ Validators.required, Validators.minLength(3), Validators.maxLength(30) ] ],
        email: [ student? student.email: null, [ Validators.email, Validators.maxLength(30) ] ],
        adress: [ student? student.adress: null, [ Validators.minLength(3), Validators.maxLength(50) ] ],
        currentYearOfStudy: [ student? student.currentYearOfStudy: null, [ Validators.required ] ],
        city: [ student? student.city: null ],
        id: [ +this.route.snapshot.paramMap.get('id') ]
      }
    );
  }

  onSubmit() {
    this.saveStudent()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      student => {
        this.toastService.show('Student saved!', {header:'Saving student', classname: 'bg-success text-light'});
        this.router.navigate(['/student/student-list']);
      },
      error => {
        this.toastService.show('Student is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving student', classname: 'bg-danger text-light'});
      }

    );
  }

  saveStudent() {
    if (this.edit) {
      return this.httpStudent.updateStudent(this.studentForm.value)
    } else {
      return this.httpStudent.insertStudent(this.studentForm.value)
    }
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.studentForm.get(componentName).dirty || this.studentForm.get(componentName).touched) && this.studentForm.get(componentName).hasError(errorCode);
  }

}
