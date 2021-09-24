import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ExamPeriod } from 'src/app/core/models/ExamPeriod';
import { HttpExamPeriodService } from 'src/app/service/http-exam-period.service';

@Component({
  selector: 'app-exam-period-details',
  templateUrl: './exam-period-details.component.html',
  styleUrls: ['./exam-period-details.component.css']
})
export class ExamPeriodDetailsComponent implements OnInit {

  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  examPeriod: ExamPeriod;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private httpExamPeriod: HttpExamPeriodService) { }

    ngOnInit(): void {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExamPeriod(id);
    }

    ngOnDestroy() {
      this.destroy$.next(true);
    }

    loadExamPeriod(id: number) {
      this.httpExamPeriod.getById(id)
      .pipe(
        takeUntil(this.destroy$)
      )
      .subscribe( examPeriod => {
        this.examPeriod = examPeriod
      });
    }

}

