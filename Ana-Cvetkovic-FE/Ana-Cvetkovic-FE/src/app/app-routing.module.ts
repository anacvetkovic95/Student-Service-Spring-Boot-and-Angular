import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'subject', loadChildren:() => import('./features/subject/subject.module').then(m => m.SubjectModule)},
  {path:'student', loadChildren:() => import('./features/student/student.module').then(m => m.StudentModule)},
  {path:'professor', loadChildren:() => import('./features/professor/professor.module').then(m => m.ProfessorModule)},
  {path:'exam-period', loadChildren:() => import('./features/exam-period/exam-period.module').then(m => m.ExamPeriodModule)},
  {path:'exam', loadChildren:() => import('./features/exam/exam.module').then(m => m.ExamModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
