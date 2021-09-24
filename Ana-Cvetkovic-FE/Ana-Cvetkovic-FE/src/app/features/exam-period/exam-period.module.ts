import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamPeriodRoutingModule } from './exam-period-routing.module';
import { ExamPeriodFormComponent } from './pages/exam-period-form/exam-period-form.component';
import { ExamPeriodListComponent } from './pages/exam-period-list/exam-period-list.component';
import { ExamPeriodDetailsComponent } from './pages/exam-period-details/exam-period-details.component';
import { SharedModule } from 'src/app/shared';


@NgModule({
  declarations: [
    ExamPeriodFormComponent,
    ExamPeriodListComponent,
    ExamPeriodDetailsComponent
  ],
  imports: [
    CommonModule,
    ExamPeriodRoutingModule,
    SharedModule,
  ]
})
export class ExamPeriodModule { }
