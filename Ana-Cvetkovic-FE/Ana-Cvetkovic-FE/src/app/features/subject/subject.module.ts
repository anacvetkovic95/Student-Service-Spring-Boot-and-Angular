import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SubjectRoutingModule } from './subject-routing.module';
import { SharedModule } from 'src/app/shared';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';
import { SubjectFormComponent } from './pages/subject-form/subject-form.component';
import { SubjectDetailsComponent } from './pages/subject-details/subject-details.component';


@NgModule({
  declarations: [
    SubjectListComponent,
    SubjectFormComponent,
    SubjectDetailsComponent
  ],
  imports: [
    CommonModule,
    SubjectRoutingModule,
    SharedModule,
  ]
})
export class SubjectModule { }
