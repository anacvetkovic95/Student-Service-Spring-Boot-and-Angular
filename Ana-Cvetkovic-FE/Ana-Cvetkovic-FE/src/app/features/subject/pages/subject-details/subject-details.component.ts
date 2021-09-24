import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'src/app/core';
import { HttpSubjectService } from 'src/app/service/http-subject.service';

@Component({
  selector: 'app-subject-details',
  templateUrl: './subject-details.component.html',
  styleUrls: ['./subject-details.component.css']
})
export class SubjectDetailsComponent implements OnInit {

  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  subject: Subject;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private httpSubject: HttpSubjectService) { }

    ngOnInit(): void {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadSubject(id);
    }

    ngOnDestroy() {
      this.destroy$.next(true);
    }

    loadSubject(id: number) {
      this.httpSubject.getById(id)
      .pipe(
        takeUntil(this.destroy$)
      )
      .subscribe( subject => {
        this.subject = subject
      });
    }

}
