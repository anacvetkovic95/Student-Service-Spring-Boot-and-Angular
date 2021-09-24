import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessorRoutingModule } from './professor-routing.module';
import { ProfessorListComponent } from './pages/professor-list/professor-list.component';
import { ProfessorDetailsComponent } from './pages/professor-details/professor-details.component';
import { ProfessorFormComponent } from './pages/professor-form/professor-form.component';
import { SharedModule } from 'src/app/shared';


@NgModule({
  declarations: [
    ProfessorListComponent,
    ProfessorDetailsComponent,
    ProfessorFormComponent
  ],
  imports: [
    CommonModule,
    ProfessorRoutingModule,
    SharedModule,
  ]
})
export class ProfessorModule { }
