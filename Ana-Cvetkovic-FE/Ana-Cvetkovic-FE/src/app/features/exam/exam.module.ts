import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamRoutingModule } from './exam-routing.module';
import { ExamListComponent } from './pages/exam-list/exam-list.component';
import { ExamFormComponent } from './pages/exam-form/exam-form.component';
import { ExamDetailsComponent } from './pages/exam-details/exam-details.component';
import { SharedModule } from 'src/app/shared';


@NgModule({
  declarations: [
    ExamListComponent,
    ExamFormComponent,
    ExamDetailsComponent
  ],
  imports: [
    CommonModule,
    ExamRoutingModule,
    SharedModule
  ]
})
export class ExamModule { }
