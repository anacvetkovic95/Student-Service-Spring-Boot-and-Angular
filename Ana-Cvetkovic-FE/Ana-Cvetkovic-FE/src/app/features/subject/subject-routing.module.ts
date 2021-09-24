import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectDetailsComponent } from './pages/subject-details/subject-details.component';
import { SubjectFormComponent } from './pages/subject-form/subject-form.component';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';

const routes: Routes = [
  {path:'subject-form/:id', component: SubjectFormComponent, data: {edit: true}},
  {path:'subject-form', component: SubjectFormComponent, data: {edit: false}},
  {path:'subject-list', component: SubjectListComponent},
  {path:'subject-details/:id', component:SubjectDetailsComponent},
  {path: '', redirectTo: 'subject-list', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }
