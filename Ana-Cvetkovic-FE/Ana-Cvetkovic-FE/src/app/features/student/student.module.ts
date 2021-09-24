import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { StudentListComponent } from './pages/student-list/student-list.component';
import { StudentFormComponent } from './pages/student-form/student-form.component';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { SharedModule } from 'src/app/shared';
import { StudentRegisterFormComponent } from './pages/student-register-form/student-register-form.component';


@NgModule({
  declarations: [
    StudentListComponent,
    StudentFormComponent,
    StudentDetailsComponent,
    StudentRegisterFormComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    SharedModule,
  ]
})
export class StudentModule { }
