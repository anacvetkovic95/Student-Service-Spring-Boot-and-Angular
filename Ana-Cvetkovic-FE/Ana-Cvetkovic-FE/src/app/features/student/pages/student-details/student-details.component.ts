import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core/models/student.model';
import { HttpStudentService } from 'src/app/service/http-student.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  student: Student;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private httpStudent: HttpStudentService) { }

    ngOnInit(): void {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadStudent(id);
    }

    ngOnDestroy() {
      this.destroy$.next(true);
    }

    loadStudent(id: number) {
      this.httpStudent.getById(id)
      .pipe(
        takeUntil(this.destroy$)
      )
      .subscribe( student => {
        this.student = student
      });
    }
}
