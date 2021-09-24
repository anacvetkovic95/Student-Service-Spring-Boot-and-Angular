import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Professor, Title } from 'src/app/core/models/professor.model';
import { City } from 'src/app/core/models/student.model';
import {Subject as SubjectObservable } from 'rxjs';
import { HttpProfessorService } from 'src/app/service/http-professor.service';
import { HttpCityService } from 'src/app/service/http-city.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from 'src/app/service/toast.service';
import { HttpTitleService } from 'src/app/service/http-title.service';
import { takeUntil } from 'rxjs/operators';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { HttpSubjectService } from 'src/app/service/http-subject.service';
import { Subject } from 'src/app/core';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorFormComponent implements OnInit {

  cities: City[];
  titles: Title[];
  subjects: Subject[];
  entitySubjects: Subject[]=[];

  professorForm:FormGroup;
  addedProfessor:Professor;
  selectedSubject: Subject;
  professorSubjects: Subject[]=[];

  addSubject=false;
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private httpProfessor:HttpProfessorService,
    private httpCity: HttpCityService,
    private httpTitle: HttpTitleService,
    private httpSubject: HttpSubjectService,
    private toastService: ToastService,
    private fb: FormBuilder,
    private route:ActivatedRoute,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.prepareData();
    this.loadCities();
    this.loadTitles();
    this.loadSubjects();
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

  loadTitles(){
    this.httpTitle.getAll().subscribe(
      response => {
        this.titles=response;
      }
    );
  }

  loadSubjects(){
    this.httpSubject.getAll().subscribe(
      response => {
        this.subjects = response;
      }
    );
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadProfessor(id);
      this.professorSubjects=this.professorForm?.value.subjects;
    } else {
      this.buildForm();
    }
  }

  loadProfessor(id:number){
    this.httpProfessor.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( prof => {
      console.log(prof);
      console.log(prof.subjects.length);
      this.addedProfessor=prof;
      this.professorSubjects=prof.subjects;
      this.buildForm(prof);
    });
  }

  buildForm(professor?:Professor){
    this.professorForm = this.fb.group(
      {
        firstName:[ professor? professor.firstName: null, [ Validators.required, Validators.minLength(3), Validators.maxLength(30) ] ],
        lastName:[ professor? professor.lastName: null,  [ Validators.required, Validators.minLength(3), Validators.maxLength(30) ] ],
        email:[ professor? professor.email: null, [ Validators.email, Validators.maxLength(30) ] ],//unique
        adress:[ professor? professor.adress: null, [ Validators.minLength(3), Validators.maxLength(50) ] ],
        city:[ professor? professor.city: null ],
        phone:[ professor? professor.phone: null, [ Validators.minLength(9), Validators.maxLength(15) ] ],
        reelectionDate:[ professor? professor.reelectionDate: null, [ Validators.required ] ],
        title:[ professor? professor.title :null ],
        subjects:[ professor? professor.subjects:this.professorSubjects ],
        id:[ +this.route.snapshot.paramMap.get('id') ]
      }
    );
  }

  onSubmit() {
    this.saveProfessor()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      prof => {
        this.toastService.show('Professor saved!', {header:'Saving professor', classname: 'bg-success text-light'});
        this.router.navigate(['/professor/professor-list']);
        this.addedProfessor=this.professorForm.value;
      },
      error => {
        this.toastService.show('Professor is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving professor', classname: 'bg-danger text-light'});
      }

    );
  }

  saveProfessor() {
    if (this.edit) {
      return this.httpProfessor.updateProfessor(this.professorForm.value)
    } else {
      return this.httpProfessor.insertProfessor(this.professorForm.value)
    }
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.professorForm.get(componentName).dirty || this.professorForm.get(componentName).touched) && this.professorForm.get(componentName).hasError(errorCode);
  }

  addSelectedSubject() {
    this.addSubject=false;
    if (this.selectedSubject != null) {
      console.log(this.selectedSubject);
      console.log(this.professorForm.value);

      if (this.professorSubjects?.findIndex(subject => subject.id===this.selectedSubject.id) < 0)
      this.professorSubjects?.push(this.selectedSubject);
    }
    console.log(this.professorSubjects);
  }

  removeSubjectAtIndex(i){
      if (this.professorForm.value.subjects) {
        this.professorForm.value.subjects.splice(i,1);
      }
  }

  onAddSubject(){
    this.addSubject=true;
  }

}

