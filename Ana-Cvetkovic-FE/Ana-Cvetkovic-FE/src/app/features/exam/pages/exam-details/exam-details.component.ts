import { Component, OnDestroy, OnInit } from '@angular/core';
import { Exam } from 'src/app/core/models/Exam';
import { Subject as SubjectObservable } from 'rxjs';
import { HttpExamService } from 'src/app/service/http-exam.service';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-exam-details',
  templateUrl: './exam-details.component.html',
  styleUrls: ['./exam-details.component.css']
})
export class ExamDetailsComponent implements OnInit, OnDestroy {

  exam: Exam;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private httpExam: HttpExamService
  ) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadExam(id);
  }

  ngOnDestroy() {
    this.destroy$.next(true);
  }

  loadExam(id: number) {
    this.httpExam.getById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( exam => {
      this.exam = exam
    });
  }
}

